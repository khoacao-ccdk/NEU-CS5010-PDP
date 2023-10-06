package Server;

import static Message.MessageIdentifier.*;

import Message.BroadcastMessage.BroadcastResponse;
import Message.Connect.ConnectResponse;
import Message.DirectMessage.DirectMessageRequest;
import Message.DirectMessage.DirectMessageResponse;
import Message.Disconnect.DisconnectResponse;
import Message.Insult.InsultResponse;
import Message.QueryUsers.QueryUsersResponse;
import Message.Response.ResponseSender;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;

/**
 * ServerThread class - represents a communication thread between the server and a client
 *
 * @author Cody Cao, Letian Shi
 */
public class ServerThread implements Runnable {

  /**
   * Error message printed when there was an error handling communication
   */
  public static final String ERROR_HANDLING_MESSAGE = "There was an error handling communication from client";

  private Server server;
  private Socket serverSocket;
  private DataInputStream in;
  private DataOutputStream out;

  //The userName that the thread is affiliated with
  private String userName;

  /**
   * Constructs a new ServerThread object
   *
   * @param server a Server object represents the server that this thread is affiliated with
   * @param socket a Socket object represents a server-client socket
   */
  public ServerThread(Server server, Socket socket) {
    this.server = server;
    this.serverSocket = socket;
  }

  /**
   * @return the Server object that the thread belongs to
   */
  public Server getServer() {
    return server;
  }

  /**
   * @return a DataInputStream object used to receive requests through socket
   */
  public DataInputStream getIn() {
    return in;
  }

  /**
   * @return a DataOutputStream object used to send responses through socket
   */
  public DataOutputStream getOut() {
    return out;
  }

  /**
   * @return a String represents the username that the thread is affiliated with
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName a String represents the username that the thread is affiliated with
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * This method is used to generate message objects for server and write to DataOutputStream. This
   * method is not used to check status of server or client.
   *
   * @param in  DataInputStream
   * @param out DataOutputStream
   * @throws IOException Exception
   */
  public synchronized void createResponse(DataInputStream in, DataOutputStream out)
      throws Exception {
    int identifier;

    //If the input is currently empty, sleep for (SLEEP_TIME) before trying again
    while (in.available() == 0) {
      Thread.sleep(Server.SLEEP_TIME);
    }

    //When there's a message, read the identifier and consume it
    identifier = in.readInt();

    ResponseSender message = null;
    switch (identifier) {
      case CONNECT_MESSAGE -> {
        message = new ConnectResponse().createResponse(in, this);
        //Add user to map after the connection is successful
        if (((ConnectResponse) message).isSuccess()) {
          server.addUser(userName, this);
        }
      }
      case DISCONNECT_MESSAGE -> {
        message = new DisconnectResponse().createResponse(in, this);
        if (((DisconnectResponse) message).isSuccess()) {
          message.sendResponse(out, this);
          this.serverSocket.close();
          return;
        }
      }
      case DIRECT_MESSAGE -> message = new DirectMessageResponse().createResponse(in, this);

      case QUERY_CONNECTED_USERS -> message = new QueryUsersResponse().createResponse(in, this);

      case BROADCAST_MESSAGE -> message = new BroadcastResponse().createResponse(in, this);

      case SEND_INSULT -> message = new InsultResponse().createResponse(in, this);
    }
    message.sendResponse(out, this);
  }

  /**
   * DataInputStream in setter
   *
   * @param in DataInputStream
   */
  public void setIn(DataInputStream in) {
    this.in = in;
  }

  /**
   * Starts a client-server communication thread
   */
  @Override
  public void run() {
    try {
      out = new DataOutputStream(serverSocket.getOutputStream());
      in = new DataInputStream(serverSocket.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
    }

    while (!serverSocket.isClosed()) {
      try {
        createResponse(in, out);
      } catch (SocketException e) {
        try {
          in.close();
          out.close();
          //In case the client closed without sending a disconnect message
          server.removeUser(this);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.err.println(ERROR_HANDLING_MESSAGE);
      }
    }
  }

  /**
   * @param o another ServerThread object
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
    ServerThread that = (ServerThread) o;
    return Objects.equals(server, that.server) && Objects.equals(serverSocket,
        that.serverSocket) && Objects.equals(userName, that.userName);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(server, serverSocket, userName);
  }
}
