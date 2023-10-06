package Message.BroadcastMessage;

import Client.ClientThread;
import Message.FailedMessage;
import Message.MessageIdentifier;
import Message.Response.ResponseReceiver;
import Message.Response.ResponseSender;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

/**
 * BroadcastResponse class - used to broadcast message to all user in the chatroom
 *
 * @author Cody Cao, Letian Shi
 */
public class BroadcastResponse implements ResponseSender, ResponseReceiver {

  /**
   * a String defines the format of a broadcast message
   */
  public static final String BROADCAST_MESSAGE_FORMAT = "%s -> @all: %s";
  private int identifier = MessageIdentifier.BROADCAST_MESSAGE;
  private int senderUsernameSize;
  private String senderUsername;
  private int messageSize;
  private String message;

  /**
   * Constructs an empty broadcast response - used for creating a response message
   */
  public BroadcastResponse() {
  }

  /**
   * Constructs a new broadcast response - used for sending the message
   *
   * @param senderUsernameSize an Integer represents the length of sender's username
   * @param senderUsername     a String represents the sender's username
   * @param messageSize        an Integer represents the length of message to broadcast
   * @param message            a String represents the message to broadcast
   */
  public BroadcastResponse(int senderUsernameSize, String senderUsername,
      int messageSize, String message) {
    this.senderUsernameSize = senderUsernameSize;
    this.senderUsername = senderUsername;
    this.messageSize = messageSize;
    this.message = message;
  }

  /**
   * @param out          a DataOutputStream object used to send responses through socket
   * @param serverThread a ServerThread object represents a server-client connection
   * @throws IOException when there is an exception sending response through socket
   */
  @Override
  public synchronized void sendResponse(DataOutputStream out, ServerThread serverThread)
      throws IOException {
    ConcurrentMap<String, ServerThread> allUser = serverThread.getServer().getAllUsers();

    //Send to all users
    for (String username : allUser.keySet()) {
      if (username.equals(senderUsername)) {
        continue;
      }
      DataOutputStream userOut = allUser
          .getOrDefault(username, null)
          .getOut();

      if (userOut != null) {
        userOut.writeInt(identifier);
        userOut.writeInt(senderUsernameSize);
        userOut.writeUTF(senderUsername);
        userOut.writeInt(messageSize);
        userOut.writeUTF(this.toString());
      }
    }
  }

  /**
   * @param in           a DataInputStream represents the input stream from socket
   * @param clientThread a ClientThread object represents the
   * @return a Response represents the Broadcast message sent from server to clients
   * @throws IOException when there is an Exception reading server's response
   */
  @Override
  public ResponseReceiver getResponse(DataInputStream in, ClientThread clientThread)
      throws IOException {
    in.readInt();
    String senderUsername = in.readUTF();
    int messageSize = in.readInt();
    String messageBody = in.readUTF();

    ResponseReceiver response = new BroadcastResponse(messageSize,
        senderUsername,
        messageSize,
        messageBody);

    clientThread.getClient().updateDisplay(messageBody);
    return response;
  }

  /**
   * @param in           a DataInputStream object used to receive responses through socket
   * @param serverThread a ServerThread object represents a server-client connection
   * @return a Response object represents the Broadcast response
   * @throws IOException when there is an exception getting requests through socket
   */
  @Override
  public ResponseSender createResponse(DataInputStream in, ServerThread serverThread)
      throws IOException {
    BroadcastRequest request = (BroadcastRequest) new BroadcastRequest().getRequest(in);
    String senderUsername = request.getSenderUsername();

    //In case the sender is not currently in the chatroom
    if (!serverThread.getServer().isValidUser(senderUsername)) {
      return new FailedMessage(FailedMessage.FAILED_SENDER_NOT_IN_SERVER);
    }

    return new BroadcastResponse(
        request.getSenderUsernameSize(),
        senderUsername,
        request.getMessageSize(),
        request.getMessage());
  }

  /**
   * @param o another BroadcastResponse object
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
    BroadcastResponse that = (BroadcastResponse) o;
    return identifier == that.identifier && Objects.equals(senderUsername,
        that.senderUsername) && Objects.equals(message, that.message);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(identifier, senderUsername, message);
  }

  /**
   * @return a String represents a broadcast message to be displayed
   */
  @Override
  public String toString() {
    return String.format(BROADCAST_MESSAGE_FORMAT, senderUsername, message);
  }
}
