package Message.DirectMessage;

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
 * DirectMessageResponse class. This class will play a role of sending message to both users when
 * succeeded. When failed, a Failed response will be handled by the FailedMessage class.
 *
 * @author Cody Cao, Letian Shi
 */
public class DirectMessageResponse implements ResponseSender, ResponseReceiver {

  /**
   * a String defines the format of a direct message
   */
  public static final String DIRECT_MESSAGE_FORMAT = "%s -> %s: %s";
  private int messageIdentifier = MessageIdentifier.DIRECT_MESSAGE;
  private int senderUsernameSize;
  private String senderUsername;
  private int receiverUsernameSize;
  private String recipientUsername;
  private int messageBodySize;
  private String messageBody;

  /**
   * Constructs a new DirectMessageResponse object
   *
   * @param senderUsername   a String represents the username of the sender
   * @param receiverUsername a String represents the username of the receiver
   * @param messageBody      a String represents teh message body
   */
  public DirectMessageResponse(String senderUsername, String receiverUsername, String messageBody) {
    this.senderUsername = senderUsername;
    this.senderUsernameSize = senderUsername.length();
    this.recipientUsername = receiverUsername;
    this.receiverUsernameSize = receiverUsername.length();
    this.messageBody = messageBody;
    this.messageBodySize = messageBody != null ? messageBody.length() : 0;
  }

  /**
   * Constructs a new DirectMessageResponse to get response from the socket
   */
  public DirectMessageResponse() {
  }

  /**
   * Send a direct message response to both sender and receiver in case the request was handled
   * successfully
   *
   * @param out          a DataOutputStream object used to send responses through socket
   * @param serverThread a ServerThread object represents a server-client connection
   * @throws IOException when there is an error sending data through socket
   */
  @Override
  public synchronized void sendResponse(DataOutputStream out, ServerThread serverThread)
      throws IOException {
    ConcurrentMap<String, ServerThread> allUser = serverThread.getServer().getAllUsers();
    ServerThread senderThread = allUser.getOrDefault(senderUsername, null);
    ServerThread receiverThread = allUser.getOrDefault(recipientUsername, null);
    if (senderThread != null && receiverThread != null) {
      sendMessageToUser(receiverThread.getOut());
    }
  }

  /**
   * Send message to a user through socket, given their DataOutputStream
   *
   * @param out a DataOutputStream object used to send message to user through socket
   * @throws IOException when there is an error sending message through socket
   */
  private void sendMessageToUser(DataOutputStream out) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeInt(senderUsernameSize);
    out.writeUTF(senderUsername);
    out.writeInt(receiverUsernameSize);
    out.writeUTF(recipientUsername);
    out.writeInt(messageBodySize);
    out.writeUTF(this.toString());
  }

  /**
   * Set message's body - used in case an insult is sent
   *
   * @param messageBody a String represents the message to be sent
   */
  public void setMessageBody(String messageBody) {
    this.messageBody = messageBody;
    this.messageBodySize = messageBody.length();
  }

  /**
   * Set another value for message identifier - in case we need to send an insult
   *
   * @param messageIdentifier a MessageIdentifier
   */
  public void setMessageIdentifier(int messageIdentifier) {
    this.messageIdentifier = messageIdentifier;
  }

  /**
   * @return messageIdentifier
   */
  public int getMessageIdentifier() {
    return messageIdentifier;
  }

  /**
   * @return messageBody
   */
  public String getMessageBody() {
    return messageBody;
  }

  /**
   * @param in           a DataInputStream object used to receive responses through socket
   * @param serverThread a ServerThread object represents a server-client connection
   * @return a Response object represents either a FailedMessage when there's a problem sending
   * direct message or a DirectMessageResponse that will be sent to both sender and receiver
   * @throws IOException when there is an error reading from DataInputStream
   */
  @Override
  public synchronized ResponseSender createResponse(DataInputStream in, ServerThread serverThread)
      throws IOException {
    DirectMessageRequest request = (DirectMessageRequest) new DirectMessageRequest().getRequest(in);

    String senderUsername = request.getSenderUsername();
    String recipientUsername = request.getRecipientUsername();
    String messageBody = request.getMessage();

    ResponseSender message = new DirectMessageResponse(senderUsername, recipientUsername,
        messageBody);

    if (!serverThread.getServer().isValidUser(senderUsername)) {
      message = new FailedMessage(FailedMessage.FAILED_SENDER_NOT_IN_SERVER);
    }

    if (!serverThread.getServer().isValidUser(recipientUsername)) {
      message = new FailedMessage(FailedMessage.FAILED_RECEIVER_NOT_IN_SERVER);
    }
    return message;
  }

  /**
   * @param in           a DataInputStream represents the input stream from socket
   * @param clientThread a ClientThread object represents the
   * @return a direct message sent from the server
   * @throws IOException when there is an error reading server's response
   */
  @Override
  public ResponseReceiver getResponse(DataInputStream in, ClientThread clientThread)
      throws IOException {
    in.readInt();
    String senderUsername = in.readUTF();
    in.readInt();
    String recipientUsername = in.readUTF();
    in.readInt();
    String messageBody = in.readUTF();

    ResponseReceiver response = new DirectMessageResponse(senderUsername, recipientUsername,
        messageBody);

    clientThread.getClient().updateDisplay(messageBody);
    return response;
  }

  /**
   * @param o another DirectMessageResponse object
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
    DirectMessageResponse that = (DirectMessageResponse) o;
    return Objects.equals(senderUsername, that.senderUsername) && Objects.equals(
        recipientUsername, that.recipientUsername) && Objects.equals(messageBody,
        that.messageBody);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(senderUsername, recipientUsername, messageBody);
  }

  /**
   * @return a String represents the message that gets displayed
   */
  @Override
  public String toString() {
    return String.format(DIRECT_MESSAGE_FORMAT,
        senderUsername,
        recipientUsername,
        messageBody);
  }
}
