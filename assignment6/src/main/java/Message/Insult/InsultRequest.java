package Message.Insult;


import static Message.MessageIdentifier.SEND_INSULT;

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
public class InsultRequest implements RequestSender, RequestReceiver {

  private int messageIdentifier = SEND_INSULT;
  private int senderUsernameSize;
  private String senderUsername;
  private int recipientUsernameSize;
  private String recipientUsername;

  /**
   * @param senderUsername    username of sender
   * @param recipientUsername username of recipient
   */
  public InsultRequest(String senderUsername, String recipientUsername) {
    this.senderUsername = senderUsername;
    this.recipientUsername = recipientUsername;
    this.senderUsernameSize = senderUsername.length();
    this.recipientUsernameSize = recipientUsername.length();
  }

  /**
   * Constructs an empty insult request - used by server to retrieve the request
   */
  public InsultRequest() {
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
  }

  @Override
  public RequestReceiver getRequest(DataInputStream in) throws IOException {
    in.readInt();
    String senderUsername = in.readUTF();
    in.readInt();
    String recipientUsername = in.readUTF();

    return new InsultRequest(senderUsername, recipientUsername);
  }

  /**
   * @return a String represents sender's username
   */
  public String getSenderUsername() {
    return senderUsername;
  }

  /**
   * @return a String represents recipient's username
   */
  public String getRecipientUsername() {
    return recipientUsername;
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
    if (!(o instanceof InsultRequest that)) {
      return false;
    }
    return messageIdentifier == that.messageIdentifier
        && senderUsernameSize == that.senderUsernameSize
        && recipientUsernameSize == that.recipientUsernameSize && Objects.equals(
        senderUsername, that.senderUsername) && Objects.equals(recipientUsername,
        that.recipientUsername);
  }

  /**
   * @return hashcode
   */
  @Override  public int hashCode() {
    return Objects.hash(messageIdentifier, senderUsernameSize, senderUsername,
        recipientUsernameSize,
        recipientUsername);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "SendInsult{" +
        "messageIdentifier=" + messageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername='" + senderUsername + '\'' +
        ", recipientUsernameSize=" + recipientUsernameSize +
        ", recipientUsername='" + recipientUsername + '\'' +
        '}';
  }
}
