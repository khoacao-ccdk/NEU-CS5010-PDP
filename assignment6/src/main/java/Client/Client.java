package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * Client class
 *
 * @author Cody Cao, Letian Shi
 */
public class Client {

  /**
   * Sleep time to wait for server-client communication
   */
  public static final int SLEEP_TIME = 100;

  /**
   * A String represents all available commands
   */
  public static final String AVAILABLE_COMMANDS = """
      Supported list of commands\s
      logoff: disconnect from the server
      who: get a list of users currently in the chatroom
      @user: sends a direct message to the specified user
      @all: sends a broadcast message to the server, to be sent to all users connected
      !user: sends a randomly generated insult message to the specified user
      ?: Show all available commands""";

  /**
   * Position of the command denotation (! or @)
   */
  public static final int COMMAND_DENOTATION_POSITION = 0;

  /**
   * Integer value to reinforce that the direct message has a body
   */
  public static final int DIRECT_MESSAGE_COMMAND_MIN_LENGTH = 2;
  /**
   * User's command to log off
   */
  public static final String LOGOFF_COMMAND = "logoff";

  /**
   * User's command to see all users in the chatroom
   */
  public static final String USER_QUERY_COMMAND = "who";

  /**
   * User's command to see all available commands
   */
  public static final String ALL_COMMAND = "?";

  /**
   * User's command to send a direct message
   */
  public static final String DIRECT_MESSAGE_COMMAND = "@";

  /**
   * User's command to send an insult
   */
  public static final String INSULT_COMMAND = "!";

  /**
   * User's command to send a broadcast message
   */
  public static final String BROADCAST_MESSAGE_COMMAND = "@all";

  /**
   * Error message when user enters an invalid input
   */
  public static final String INVALID_INPUT_ERROR = "Invalid input. Please try again or use ? for a list of available commands";

  /**
   * Error message when the server could not be reached
   */
  public static final String UNKNOWN_HOST_ERROR = "Could not reach host at %s:%d";

  /**
   * Error message when there was an error connecting to server
   */
  public static final String CONNECTION_ERROR = "Fatal Connection error. Check if the server is online before starting a client. Additionally, check if you have input the correct host and port";

  /**
   * Error message when trying to shut down server
   */
  public static final String ERROR_SHUTTING_DOWN = "An error occurred when trying to shutdown client";
  private String userName;
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader input;
  private ClientState state;

  /**
   * Constructs a new client
   *
   * @param userName a String represents the username
   * @param host     a String represents the host's ip address
   * @param port     an Integer represents the host's port
   */
  public Client(String userName, String host, int port) {
    this.userName = userName;
    this.host = host;
    this.port = port;
  }


  /**
   * Starts a client. The client, when starts, will ask for the username and try to connect to the
   * server. If the server connection was successful, a message will be printed stating the number
   * of users currently in the chatroom. If failed, there will be a message explaining why
   *
   * @param sc a Scanner object used to read user's input
   */
  public void startClient(BufferedReader sc) {
    this.state = ClientState.CONNECTED;
    this.input = sc;
    try {
      this.socket = new Socket(host, port);
      Thread.sleep(SLEEP_TIME); // waiting for network Communication.
    } catch (UnknownHostException e) {
      System.err.println(String.format(UNKNOWN_HOST_ERROR, host, port));
      System.exit(0);
    } catch (IOException e) {
      System.err.println(CONNECTION_ERROR);
      System.exit(0);
    } catch (InterruptedException e) {
      System.err.println(e.getMessage());
      System.exit(0);
    }

    ClientThread serverThread = new ClientThread(this, socket, userName);
    Thread serverAccessThread = new Thread(serverThread);
    serverAccessThread.start();

    startInputThread(serverThread);
  }

  /**
   * @return client state
   */
  public ClientState getState() {
    return state;
  }

  /**
   * Starts a Thread that is used to listen for user input
   *
   * @param serverThread a ClientThread object used to pass user's command to the server
   *                     communication thread
   */
  public void startInputThread(ClientThread serverThread) {
    Thread inputThread = new Thread(() -> {
      while (this.state == ClientState.CONNECTED) {
        try {
          while (!input.ready()) {
            Thread.sleep(SLEEP_TIME);
          }
          String input = this.input.readLine();
          if (isValidInput(input)) {
            serverThread.addMessage(input);
          }
        }
        //IOException will be thrown when we stop the client and close the input stream -> ignore the exception
        catch (IOException e) {
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    inputThread.start();
  }

  /**
   * Validate user's input
   *
   * @param input a String represents user's input
   * @return whether the user's command is a valid command
   */
  public boolean isValidInput(String input) {
    input = input.trim();
    if (input.equals(LOGOFF_COMMAND)) {
      return true;
    }

    if (input.equals(USER_QUERY_COMMAND)) {
      return true;
    }

    if (input.equals(ALL_COMMAND)) {
      updateDisplay(AVAILABLE_COMMANDS);
      return false;
    }

    //The space condition makes sure that there's a message body
    if (input.contains(BROADCAST_MESSAGE_COMMAND) && input.contains(" ")) {
      return true;
    }

    //The space condition makes sure that there's a message body
    if (input.indexOf(DIRECT_MESSAGE_COMMAND) == COMMAND_DENOTATION_POSITION
        && input.length() >= DIRECT_MESSAGE_COMMAND_MIN_LENGTH
        && input.contains(" ")) {
      return true;
    }

    if (input.indexOf(INSULT_COMMAND) == COMMAND_DENOTATION_POSITION
        && input.length() >= DIRECT_MESSAGE_COMMAND_MIN_LENGTH
        && !input.contains(" ")) {
      return true;
    }
    updateDisplay(INVALID_INPUT_ERROR);
    return false;
  }

  /**
   * Update client's display - currently only printing to console
   *
   * @param message a String represents the
   */
  public synchronized void updateDisplay(String message) {

    System.out.println(message);
  }

  /**
   * Close socket connection to the server, change client status to disconnected and stop listening
   * for user input
   */
  public synchronized void closeClient() {
    try {
      this.state = ClientState.DISCONNECTED;
      this.socket.close();
      this.input.close();
    } catch (IOException e) {
      System.err.println(ERROR_SHUTTING_DOWN);
    }
  }

  /**
   * @param o another Client object
   * @return true if the two objects are equal structurally
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Client client = (Client) o;
    return port == client.port && Objects.equals(userName, client.userName)
        && Objects.equals(host, client.host) && Objects.equals(socket,
        client.socket) && Objects.equals(input, client.input) && state == client.state;
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(userName, host, port, socket, input, state);
  }


  /**
   * @return a String with object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Client{");
    sb.append("userName='").append(userName).append('\'');
    sb.append(", host='").append(host).append('\'');
    sb.append(", port=").append(port);
    sb.append(", input=").append(input);
    sb.append(", state=").append(state);
    sb.append('}');
    return sb.toString();
  }
}