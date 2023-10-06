package Message.DirectMessage;


import static Message.MessageIdentifier.DIRECT_MESSAGE;

import Client.Client;
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
public class DirectMessageRequest implements RequestSender, RequestReceiver {

  private int messageIdentifier = DIRECT_MESSAGE;
  private int senderUsernameSize;
  private String senderUsername;
  private int recipientUsernameSize;
  private String recipientUsername;
  private int messageSize;
  private String message;

  /**
   * @param username a String represents the sender's username
   * @param command  a String represents the sender's command on client's side
   * @return a DirectMessage request from the sender's command on the client
   */
  public static DirectMessageRequest getRequestFromCommand(String username, String command) {
    int splitPosition = command.indexOf(" ");
    int subStringStartPos = 0;
    String recipientUsername = command.substring(subStringStartPos, splitPosition)
        .replace(Client.DIRECT_MESSAGE_COMMAND, "");
    String messageBody = command
        .substring(splitPosition, command.length())
        .trim();

    return new DirectMessageRequest(username, recipientUsername, messageBody);
  }

  /**
   * @param senderUsername    username of sender
   * @param recipientUsername username of recipient
   * @param message           message sent
   */
  public DirectMessageRequest(String senderUsername, String recipientUsername, String message) {
    this.senderUsername = senderUsername;
    this.recipientUsername = recipientUsername;
    this.message = message;
    this.senderUsernameSize = senderUsername.length();
    this.recipientUsernameSize = recipientUsername.length();
    this.messageSize = message.length();
  }

  /**
   * Constructs an empty DirectMessage request - used to get request's data
   */
  public DirectMessageRequest() {
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
    out.writeInt(recipientUsernameSize);
    out.writeUTF(recipientUsername);
    out.writeInt(messageSize);
    out.writeUTF(message);
  }

  @Override
  public RequestReceiver getRequest(DataInputStream in) throws IOException {
    int senderUserNameSize = in.readInt();
    String senderUsername = in.readUTF();
    int receiverUserNameSize = in.readInt();
    String receiverUserName = in.readUTF();
    int messageSize = in.readInt();
    String messageBody = in.readUTF();

    RequestReceiver req = new DirectMessageRequest(senderUsername, receiverUserName, messageBody);
    return req;
  }

  /**
   * @return an Integer represents the sender's username size
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
   * @return an Integer represents the recipient's username size
   */
  public int getRecipientUsernameSize() {
    return recipientUsernameSize;
  }

  /**
   * @return a String represents the recipient's username
   */
  public String getRecipientUsername() {
    return recipientUsername;
  }

  /**
   * @return an Integer represents the message's size
   */
  public int getMessageSize() {
    return messageSize;
  }

  /**
   * @return a String represents the message
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
    if (!(o instanceof DirectMessageRequest that)) {
      return false;
    }
    return messageIdentifier == that.messageIdentifier
        && senderUsernameSize == that.senderUsernameSize
        && recipientUsernameSize == that.recipientUsernameSize && messageSize == that.messageSize
        && Objects.equals(senderUsername, that.senderUsername) && Objects.equals(
        recipientUsername, that.recipientUsername) && Objects.equals(message, that.message);
  }

  /**
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(messageIdentifier, senderUsernameSize, senderUsername,
        recipientUsernameSize,
        recipientUsername, messageSize, message);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "DirectMessage{" +
        "messageIdentifier=" + messageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername='" + senderUsername + '\'' +
        ", recipientUsernameSize=" + recipientUsernameSize +
        ", recipientUsername='" + recipientUsername + '\'' +
        ", messageSize=" + messageSize +
        ", message='" + message + '\'' +
        '}';
  }
}
