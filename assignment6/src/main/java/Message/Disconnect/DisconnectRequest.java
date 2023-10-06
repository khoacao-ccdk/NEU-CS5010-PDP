package Message.Disconnect;


import static Message.MessageIdentifier.DISCONNECT_MESSAGE;

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
public class DisconnectRequest implements RequestSender, RequestReceiver {

  private int messageIdentifier = DISCONNECT_MESSAGE;
  private int sizeUsername;
  private String username;

  /**
   * Constructs a new DisconnectMessage to be sent from the Client
   *
   * @param username client username
   */
  public DisconnectRequest(String username) {
    this.username = username;
    this.sizeUsername = username.length();
  }

  /**
   * Empty constructor - used to get request from client
   */
  public DisconnectRequest() {
  }

  /**
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception when there's an error writing to socket
   */
  @Override
  public void sendRequest(DataOutputStream out, ClientThread serverThread) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeInt(sizeUsername);
    out.writeUTF(username);
  }

  /**
   * @param in a DataInputStream object used to receive requests through socket
   * @return a RequestReceiver object represents the request from client
   * @throws IOException when there's an error reading from socket
   */
  @Override
  public RequestReceiver getRequest(DataInputStream in) throws IOException {
    in.readInt();
    String username = in.readUTF();
    return new DisconnectRequest(username);
  }

  /**
   * @return a String represents the username to disconnect
   */
  public String getUsername() {
    return username;
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
    if (!(o instanceof DisconnectRequest that)) {
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
    return "DisconnectMessage{" +
        "messageIdentifier=" + messageIdentifier +
        ", sizeUsername=" + sizeUsername +
        ", username='" + username + '\'' +
        '}';
  }
}
