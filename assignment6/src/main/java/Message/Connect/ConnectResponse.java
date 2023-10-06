package Message.Connect;


import static Message.MessageIdentifier.CONNECT_RESPONSE;

import Client.Client;
import Client.ClientThread;
import Message.MessageIdentifier;
import Message.Response.ResponseReceiver;
import Message.Response.ResponseSender;
import Server.ServerThread;
import Server.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Cody Cao, Letian Shi
 */
public class ConnectResponse implements ResponseSender, ResponseReceiver {

  /**
   * Error message when the client expecting a CONNECT_RESPONSE from the server but getting a
   * different code;
   */
  public static final String UNEXPECTED_CONNECT_RESPONSE = "Expecting a connect response, received something else!";

  /**
   * Connection successful message
   */
  public static final String SUCCESSFUL_MESSAGE = "There are %d other connected client";

  /**
   * Connection unsuccessful message, sent when the room is currently full
   */
  public static final String UNSUCCESSFUL_ROOM_FULL = "The room is currently full, please try again later";

  /**
   * Connection unsuccessful message, sent when there's already another user with the same name in
   * the room
   */
  public static final String UNSUCCESSFUL_DUPLICATE_USERNAME = "There is already a client with the same username in the room";

  /**
   * an Integer value represents the message identifier
   */
  private final int messageIdentifier = CONNECT_RESPONSE;
  private boolean success;
  private int msgSize;
  private String message;
  private String userName;

  /**
   * @param success boolean of if the connection process is successful
   * @param message message sent
   */
  public ConnectResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
    this.msgSize = message.length();
  }

  /**
   * Constructs a new ConnectResponse object
   */
  public ConnectResponse() {
  }

  /**
   * Set userName - used when the connection was successful
   *
   * @param userName a String represents the userName
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the username - used when the connection was successful
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @return whether the connect/disconnect request was successful
   */
  public boolean isSuccess() {
    return success;
  }

  /**
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception
   */
  @Override
  public void sendResponse(DataOutputStream out, ServerThread serverThread) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeBoolean(success);
    out.writeInt(msgSize);
    out.writeUTF(message);
  }

  @Override
  public synchronized ResponseSender createResponse(DataInputStream in, ServerThread serverThread)
      throws IOException {
    int size = in.readInt();
    String username = in.readUTF();
    ConcurrentMap<String, ServerThread> serverThreads = serverThread
        .getServer()
        .getAllUsers();

    int numClients = serverThreads.size();

    ResponseSender message = new ConnectResponse(true,
        String.format(ConnectResponse.SUCCESSFUL_MESSAGE, numClients));
    if (numClients >= Server.CLIENT_LIMIT) {
      message = new ConnectResponse(false, ConnectResponse.UNSUCCESSFUL_ROOM_FULL);
    }

    if (serverThreads.containsKey(username)) {
      message = new ConnectResponse(false, ConnectResponse.UNSUCCESSFUL_DUPLICATE_USERNAME);
      ((ConnectResponse) message).setUserName(username);
    }

    if (((ConnectResponse) message).isSuccess()) {
      serverThread.setUserName(username);
    }
    return message;
  }

  @Override
  public ResponseReceiver getResponse(DataInputStream in, ClientThread clientThread)
      throws IOException {
    ResponseReceiver response = null;
    try {
      //Wait for server's connect response
      while (in.available() == 0) {
        Thread.sleep(Client.SLEEP_TIME);
      }

      int identifier = in.readInt();
      if (identifier != MessageIdentifier.CONNECT_RESPONSE) {
        System.err.println(UNEXPECTED_CONNECT_RESPONSE);
      }
      boolean isSuccessful = in.readBoolean();
      int messageSize = in.readInt();
      String message = in.readUTF();

      response = new ConnectResponse(isSuccessful, message);
      clientThread.getClient().updateDisplay(message);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (InterruptedException e) {
      System.err.println(e.getMessage());
    }
    return response;
  }

  /**
   * @param o object to be tested
   * @return true if the same, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ConnectResponse that)) {
      return false;
    }
    return messageIdentifier == that.messageIdentifier && success == that.success
        && msgSize == that.msgSize && Objects.equals(message, that.message);
  }

  /**
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(messageIdentifier, success, msgSize, message);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "ConnectResponse{" +
        "messageIdentifier=" + messageIdentifier +
        ", success=" + success +
        ", msgSize=" + msgSize +
        ", message='" + message + '\'' +
        '}';
  }

}
