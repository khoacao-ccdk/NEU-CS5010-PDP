package Message.QueryUsers;


import static Message.MessageIdentifier.QUERY_CONNECTED_USERS;

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
public class QueryUsersRequest implements RequestSender, RequestReceiver {

  private int messageIdentifier = QUERY_CONNECTED_USERS;
  private int sizeUsername;
  private String username;

  /**
   * @param username client username
   */
  public QueryUsersRequest(String username) {
    this.username = username;
    this.sizeUsername = username.length();
  }

  /**
   * Constructs an empty QueryUsersRequest object - to get data from socket
   */
  public QueryUsersRequest() {
  }

  /**
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception
   */
  @Override
  public void sendRequest(DataOutputStream out, ClientThread serverThread) throws IOException {
    out.writeInt(messageIdentifier);
    out.writeInt(sizeUsername);
    out.writeUTF(username);
  }

  /**
   * Used to read the request from client - server side
   *
   * @param in a DataInputStream object used to receive requests through socket
   * @return a Request object represents user's request for others' username
   * @throws IOException when there is an error reading request from client
   */
  @Override
  public RequestReceiver getRequest(DataInputStream in) throws IOException {
    int sizeUsername = in.readInt();
    String userName = in.readUTF();
    return new QueryUsersRequest(userName);
  }

  /**
   * @return a String represents the username
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
    if (!(o instanceof QueryUsersRequest that)) {
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
    return "QueryUsers{" +
        "messageIdentifier=" + messageIdentifier +
        ", sizeUsername=" + sizeUsername +
        ", username='" + username + '\'' +
        '}';
  }
}
