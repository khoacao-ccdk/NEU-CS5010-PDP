package Client;

import Message.BroadcastMessage.BroadcastRequest;
import Message.BroadcastMessage.BroadcastResponse;
import Message.Connect.ConnectRequest;
import Message.Connect.ConnectResponse;
import Message.DirectMessage.DirectMessageRequest;
import Message.DirectMessage.DirectMessageResponse;
import Message.Disconnect.DisconnectRequest;
import Message.FailedMessage;
import Message.MessageIdentifier;
import Message.QueryUsers.QueryUsersRequest;
import Message.Insult.InsultRequest;
import Message.QueryUsers.QueryUsersResponse;
import Message.Request.RequestSender;
import Message.Response.ResponseReceiver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Objects;

/**
 * ClientThread class - represents a communication thread between the client and the server
 *
 * @author Cody Cao, Letian Shi
 */
public class ClientThread implements Runnable {

  /**
   * Error message when there's an error reading from the server
   */
  public static final String RESPONSE_READING_ERROR = "There was an error reading response from the server";

  private Socket socket;
  private String username;
  private Client client;
  /**
   * a LinkedList used by the Client to push user's command for this object to handle
   */
  private final LinkedList<String> messagesToSend;
  private boolean hasMessages = false;

  /**
   * Constructs a new ClientThread object
   *
   * @param client   a Client object represents the client that the thread is affiliated with
   * @param socket   a Socket object represents the socket that the client is using to connect with
   *                 the server
   * @param username a String represents the username of the client
   */
  public ClientThread(Client client, Socket socket, String username) {
    this.client = client;
    this.socket = socket;
    this.username = username;
    messagesToSend = new LinkedList<>();
  }

  /**
   * Add a command to server thread to handle
   * @param message a String represents the user's command
   */
  public void addMessage(String message) {
    synchronized (messagesToSend) {
      hasMessages = true;
      messagesToSend.push(message);
    }
  }

  /**
   * messagesToSend getter
   *
   * @return messagesToSend linkedlist
   */
  public LinkedList<String> getMessagesToSend() {
    return messagesToSend;
  }

  /**
   * @return a Client that the thread is affiliated with
   */
  public Client getClient() {
    return client;
  }

  /**
   * Send a connection message to the server
   *
   * @param in  a DataInputStream object used to receive responses through socket
   * @param out a DataOutputStream object used to send requests through socket
   */
  public synchronized void sendConnectMessage(DataInputStream in, DataOutputStream out) {
    RequestSender connectMessage = new ConnectRequest(username);
    try {
      connectMessage.sendRequest(out, this);
      ConnectResponse connectResponse = (ConnectResponse) new ConnectResponse().getResponse(in,
          this);

      //If the connection could not be made, close the client
      if (!connectResponse.isSuccess()) {
        client.closeClient();
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * username setter
   *
   * @param username username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Send a disconnect message to the server
   *
   * @param in  a DataInputStream object used to receive responses through socket
   * @param out a DataOutputStream object used to send requests through socket
   */
  public void sendDisconnectMessage(DataInputStream in, DataOutputStream out) {
    RequestSender disconnectMessage = new DisconnectRequest(username);
    try {
      disconnectMessage.sendRequest(out, this);

      ConnectResponse disconnectResponse =
          (ConnectResponse) new ConnectResponse().getResponse(in, this);
      //If the connection could not be make, close the client
      if (disconnectResponse.isSuccess()) {
        client.closeClient();
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Sends a request to the server based on client's input
   *
   * @param in      a DataInputStream object used to receive responses through socket
   * @param out     a DataOutputStream object used to send requests through socket
   * @param command a String represents the user's command
   */
  public void sendRequest(DataInputStream in, DataOutputStream out, String command) {
    RequestSender requestSender = null;
    //Send a disconnect request to the server
    if (command.equals(Client.LOGOFF_COMMAND)) {
      sendDisconnectMessage(in, out);
      return;
    }
    //Send a user query request to the server
    else if (command.equals(Client.USER_QUERY_COMMAND)) {
      requestSender = new QueryUsersRequest(username);
    }
    //Send a broadcast message request
    else if (command.contains(Client.BROADCAST_MESSAGE_COMMAND)) {
      requestSender = BroadcastRequest.getRequestFromCommand(username, command);
    }
    //Send a direct message request
    else if (command.indexOf(Client.DIRECT_MESSAGE_COMMAND) == Client.COMMAND_DENOTATION_POSITION) {
      requestSender = DirectMessageRequest.getRequestFromCommand(username, command);
    }
    //Send an insult message request
    else if (command.indexOf(Client.INSULT_COMMAND) == Client.COMMAND_DENOTATION_POSITION) {
      String recipientUsername = command.replace(Client.INSULT_COMMAND, "");
      requestSender = new InsultRequest(username, recipientUsername);
    }

    try {
      requestSender.sendRequest(out, this);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Consumes a response from the server
   *
   * @param in a DataInputStream represents the socket's input stream that carry server's response
   */
  public synchronized void consumeResponse(DataInputStream in) {
    try {
      int responseCode = in.readInt();
      switch (responseCode) {
        case MessageIdentifier.DIRECT_MESSAGE, MessageIdentifier.SEND_INSULT ->
            new DirectMessageResponse().getResponse(in, this);

        case MessageIdentifier.BROADCAST_MESSAGE -> new BroadcastResponse().getResponse(in, this);

        case MessageIdentifier.QUERY_USER_RESPONSE ->
            new QueryUsersResponse().getResponse(in, this);

        case MessageIdentifier.FAILED_MESSAGE -> new FailedMessage().getResponse(in, this);

      }
    } catch (IOException e) {
      System.err.println(RESPONSE_READING_ERROR);
    }
  }

  /**
   * Starts a client to server communication thread
   */
  @Override
  public void run() {
    try {
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      DataInputStream in = new DataInputStream(socket.getInputStream());
      sendConnectMessage(in, out);

      while (!socket.isClosed()) {
        if (in.available() > 0) {
          consumeResponse(in);
        }
        if (hasMessages) {
          String message;
          synchronized (messagesToSend) {
            message = messagesToSend.pop();
            hasMessages = !messagesToSend.isEmpty();
            sendRequest(in, out, message);
          }
        }
        try {
          Thread.sleep(Client.SLEEP_TIME);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

      try {
        in.close();
        out.close();
      } catch (IOException e) {
        System.err.println(Client.ERROR_SHUTTING_DOWN);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * @param o another ClientThread object
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
    ClientThread that = (ClientThread) o;
    return Objects.equals(socket, that.socket) && Objects.equals(username,
        that.username) && Objects.equals(client, that.client) && Objects.equals(
        messagesToSend, that.messagesToSend);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(socket, username, client, messagesToSend);
  }
}
