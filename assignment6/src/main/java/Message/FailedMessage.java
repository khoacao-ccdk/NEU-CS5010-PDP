package Message;


import static Message.MessageIdentifier.FAILED_MESSAGE;

import Client.ClientThread;
import Message.Response.ResponseReceiver;
import Message.Response.ResponseSender;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Cody Cao, Letian Shi
 */
public class FailedMessage implements ResponseSender, ResponseReceiver {

  /**
   * A String that is sent when the sender is not in the chatroom
   */
  public static final String FAILED_SENDER_NOT_IN_SERVER = "There was an error delivering the message. You are not in the room right now";

  /**
   * A String that is sent when the receiver is not in the chatroom
   */
  public static final String FAILED_RECEIVER_NOT_IN_SERVER = "There was an error delivering the message. The user you want to send message to is not in the room right now";
  private int messageIdentifier = FAILED_MESSAGE;
  private int messageSize;
  private String message;

  /**
   * @param message message sent
   */
  public FailedMessage(String message) {
    this.message = message;
    this.messageSize = message.length();
  }

  /**
   * Empty constructor - used to retrieve response from server
   */
  public FailedMessage() {
  }

  /**
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception
   */
  @Override
  public void sendResponse(DataOutputStream out, ServerThread serverThread) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeInt(messageSize);
    out.writeUTF(message);
  }

  @Override
  public ResponseSender createResponse(DataInputStream in, ServerThread clientThread) throws IOException {
    return null;
  }

  @Override
  public ResponseReceiver getResponse(DataInputStream in, ClientThread clientThread) throws IOException {
    int messageSize = in.readInt();
    String message = in.readUTF();
    clientThread.getClient().updateDisplay(message);

    return new FailedMessage(message);
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
    if (!(o instanceof FailedMessage that)) {
      return false;
    }
    return messageIdentifier == that.messageIdentifier && messageSize == that.messageSize
        && Objects.equals(message, that.message);
  }

  /**
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(messageIdentifier, messageSize, message);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "FailedMessage{" +
        "messageIdentifier=" + messageIdentifier +
        ", messageSize=" + messageSize +
        ", message='" + message + '\'' +
        '}';
  }
}
