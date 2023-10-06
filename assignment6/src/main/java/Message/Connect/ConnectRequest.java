package Message.Connect;


import static Message.MessageIdentifier.CONNECT_MESSAGE;


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

public class ConnectRequest implements RequestSender, RequestReceiver {

  private final int messageIdentifier = CONNECT_MESSAGE;
  private int sizeUsername;
  private String username;

  /**
   * @param username client username
   */
  public ConnectRequest(String username) {
    this.username = username;
    this.sizeUsername = username.length();
  }

  /**
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception
   */
  public void sendRequest(DataOutputStream out, ClientThread serverThread) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeInt(sizeUsername);
    out.writeUTF(username);
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
    if (!(o instanceof ConnectRequest that)) {
      return false;
    }
    return messageIdentifier == that.messageIdentifier && sizeUsername == that.sizeUsername
        && Objects.equals(username, that.username);
  }

  /**
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(messageIdentifier, sizeUsername, username);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    return "ConnectMessage{" +
        "messageIdentifier=" + messageIdentifier +
        ", sizeUsername=" + sizeUsername +
        ", username='" + username + '\'' +
        '}';
  }

  @Override
  public RequestReceiver getRequest(DataInputStream in) throws IOException {

    return null;
  }
}
