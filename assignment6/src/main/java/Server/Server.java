package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Server class - represents a server
 *
 * @author Cody Cao, Letian Shi
 */
public class Server {

  /**
   * an Integer represents sleep time between communications
   */
  public static final int SLEEP_TIME = 50;

  /**
   * Integer value stating that server is needing any available port
   */
  public static final int SERVER_PORT_ANY = 0;

  /**
   * Maximum number of clients can be supported at the same time by the server
   */
  public static final int CLIENT_LIMIT = 10;

  /**
   * Error message when there was an error starting server
   */
  public static final String ERROR_STARTING_SERVER = "Could not start server on %s:%s";

  /**
   * Error when a new client could not be connected
   */
  public static final String CLIENT_CONNECT_ERROR = "Could not connect client on port: ";

  /**
   * Message when the server was successfully launched
   */
  public static final String SERVER_STARTED_MESSAGE = "Server listening on %s:%s";

  /**
   * Message when a new client is connected
   */
  public static final String CLIENT_CONNECTED_MESSAGE = "New client connected on port: ";

  /**
   * Message when the client exit without sending a disconnect request
   */
  public static final String CLIENT_NOT_DISCONNECTED_PROPERLY = "Client %s did not disconnect properly";

  /**
   * Message when the server has been stopped
   */
  public static final String SERVER_STOPPED_MESSAGE = "Server stopped";

  private String serverHost;
  private int serverPort;
  private ConcurrentMap<String, ServerThread> allUsers;
  private ServerState state;
  private ServerSocket serverSocket;

  /**
   * Constructs a new server
   **/
  public Server() {
  }

  /**
   * Open a socket and starts listening for connections
   */
  public void startServer() {
    this.allUsers = new ConcurrentHashMap<>();
    try {
      this.serverSocket = new ServerSocket(SERVER_PORT_ANY);
      this.serverHost = serverSocket.getInetAddress().getHostAddress();
      this.serverPort = serverSocket.getLocalPort();
      this.state = ServerState.ACTIVE;
      listenForClients(serverSocket);
    } catch (IOException e) {
      System.out.println(String.format(ERROR_STARTING_SERVER, serverHost, serverPort));
      System.exit(1);
    }
  }

  /**
   * Starts listening for incoming request from clients
   *
   * @param serverSocket a Socket represents the server's currently open socket
   */
  private void listenForClients(ServerSocket serverSocket) {
    System.out.println(String.format(SERVER_STARTED_MESSAGE, serverHost, serverPort));

    //Create another thread to listen to connections
    Thread acceptThread = new Thread(() -> {
      while (state == ServerState.ACTIVE) {
        try {
          Socket socket = serverSocket.accept();

          //Start a server thread to communicate with clients
          System.out.println(CLIENT_CONNECTED_MESSAGE + socket.getRemoteSocketAddress());
          ServerThread serverThread = new ServerThread(this, socket);
          Thread thread = new Thread(serverThread);
          thread.start();
        } catch (SocketException e) {
          System.out.println(SERVER_STOPPED_MESSAGE);
        } catch (IOException ex) {
          ex.printStackTrace();
          System.out.println(CLIENT_CONNECT_ERROR + serverPort);
        }
      }
    });
    acceptThread.start();
  }

  /**
   * Check whether the request comes from a valid user
   *
   * @param userName a String represents the username that sends the request
   * @return whether the username is valid (i.e, in currently connecting to the server)
   */
  public synchronized boolean isValidUser(String userName) {
    return allUsers.containsKey(userName);
  }

  /**
   * @return a Set of String represents the users currently connected to the server
   */
  public synchronized Set<String> getUsernames() {
    return allUsers.keySet();
  }


  /**
   * Stop the current server
   */
  public synchronized void stopServer() {
    this.state = ServerState.TERMINATED;
    try {
      this.serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add user's connection to the map
   *
   * @param userName     a String represents the userName
   * @param serverThread ServerThread represents the connection between user and server
   */
  public synchronized void addUser(String userName, ServerThread serverThread) {
    allUsers.putIfAbsent(userName, serverThread);
  }

  /**
   * Remove a user's connection from the Map
   *
   * @param userName a String represents userName of the user to remove
   */
  public synchronized void removeUser(String userName) {
    allUsers.remove(userName);
  }

  /**
   * Remove a user's connection from the Map - in case the client closed without sending a
   * disconnect message
   *
   * @param threadToRemove a String represents userName of the user to remove
   */
  public synchronized void removeUser(ServerThread threadToRemove) {
    String userNameToRemove = null;
    for (String userName : allUsers.keySet()) {
      //Make sure that the pointer is the same, nut the structure
      if (allUsers.getOrDefault(userName, null) == threadToRemove) {
        userNameToRemove = userName;
        break;
      }
    }
    if (userNameToRemove != null) {
      allUsers.remove(userNameToRemove);
      System.out.println(String.format(CLIENT_NOT_DISCONNECTED_PROPERLY, userNameToRemove));
    }
  }

  /**
   * @return a ConcurrentMap with all user that are connected to the chatroom
   */
  public ConcurrentMap<String, ServerThread> getAllUsers() {
    return allUsers;
  }

  /**
   * @param allUsers a Map contains information of all users
   */
  public void setAllUsers(
      ConcurrentMap<String, ServerThread> allUsers) {
    this.allUsers = allUsers;
  }

  /**
   * @return a String represents server host
   */
  public String getServerHost() {
    return serverHost;
  }

  /**
   * @return an Integer represents server's port number
   */
  public int getServerPort() {
    return serverPort;
  }

  /**
   * @return a ServerState enum represents whether the server is active or not
   */
  public ServerState getState() {
    return state;
  }

  /**
   * @param o another Server object
   * @return true if the two objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Server server = (Server) o;
    return serverPort == server.serverPort && Objects.equals(serverHost, server.serverHost)
        && Objects.equals(allUsers, server.allUsers) && state == server.state;
  }

  /**
   * @return an Integer represents the server's hashcode
   */

  @Override
  public int hashCode() {
    return Objects.hash(getServerHost(), getServerPort(), getState(), serverSocket);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "Server{" +
        "serverHost='" + serverHost + '\'' +
        ", serverPort=" + serverPort +
        ", state=" + state +
        ", serverSocket=" + serverSocket +
        '}';
  }
}
