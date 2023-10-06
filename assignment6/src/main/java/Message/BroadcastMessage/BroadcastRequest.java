package Message.BroadcastMessage;


import static Message.MessageIdentifier.BROADCAST_MESSAGE;

import Client.ClientThread;
import Message.Request.RequestReceiver;
import Message.Request.RequestSender;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Cody Cao, Letian Shi
 */
public class BroadcastRequest implements RequestSender, RequestReceiver {

  /**
   * an Integer represents the identifier
   */
  private final int messageIdentifier = BROADCAST_MESSAGE;
  private int senderUsernameSize;
  private String senderUsername;
  private int messageSize;
  private String message;

  /**
   * @param username a String represents the sender's username
   * @param command  a String represents the sender's command on client's side
   * @return a Broadcast request from the sender's command on the client
   */
  public static BroadcastRequest getRequestFromCommand(String username, String command) {
    int splitPosition = command.indexOf(" ");
    String messageBody = command
        .substring(splitPosition)
        .trim();
    return new BroadcastRequest(username, messageBody);
  }

  /**
   * @param senderUsername username of sender, in byte array
   * @param message        message sent, in byte array
   */
  public BroadcastRequest(String senderUsername, String message) {
    this.senderUsername = senderUsername;
    this.message = message;
    this.messageSize = message.length();
    this.senderUsernameSize = senderUsername.length();
  }

  /**
   * Constructs a new BroadcastRequest object
   */
  public BroadcastRequest() {
  }

  /**
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception
   */
  @Override
  public void sendRequest(DataOutputStream out, ClientThread serverThread) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeInt(senderUsernameSize);
    out.writeUTF(senderUsername);
    out.writeInt(messageSize);
    out.writeUTF(message);
  }

  /**
   * Returns a request object to the server
   *
   * @param in a DataInputStream object used to receive requests through socket
   * @return a RequestReceiver object represents the request message from client to server
   * @throws IOException when there is an Exception when reading from socket
   */
  @Override
  public RequestReceiver getRequest(DataInputStream in) throws IOException {
    in.readInt();
    String senderUsername = in.readUTF();
    in.readInt();
    String message = in.readUTF();
    return new BroadcastRequest(senderUsername, message);
  }

  /**
   * @return an Integer represents the length of sender's username
   */
  public int getSenderUsernameSize() {
    return senderUsernameSize;
  }

  /**
   * @return a String represents the sender's username
   */
  public String getSenderUsername() {
    return senderUsername;
  }

  /**
   * @return an Integer represents the length of message to broadcast
   */
  public int getMessageSize() {
    return messageSize;
  }

  /**
   * @return a String represents the message to broadcast
   */
  public String getMessage() {
    return message;
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
    if (!(o instanceof BroadcastRequest that)) {
      return false;
    }
    return senderUsernameSize == that.senderUsernameSize && messageSize == that.messageSize
        && Objects.equals(senderUsername, that.senderUsername) && Objects.equals(
        message, that.message);
  }

  /**
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(messageIdentifier, senderUsernameSize, senderUsername, messageSize,
        message);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "BroadcastMessage{" +
        "messageIdentifier=" + messageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername='" + senderUsername + '\'' +
        ", messageSize=" + messageSize +
        ", message='" + message + '\'' +
        '}';
  }
}
