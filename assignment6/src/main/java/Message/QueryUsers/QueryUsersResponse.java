package Message.QueryUsers;


import static Message.MessageIdentifier.QUERY_USER_RESPONSE;

import Client.ClientThread;
import Message.Response.ResponseReceiver;
import Message.Response.ResponseSender;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Cody Cao, Letian Shi
 */
public class QueryUsersResponse implements ResponseSender, ResponseReceiver {

  /**
   * String that displays the header of the user query response
   */
  public static final String RESPONSE_HEADER = "List of other connected users: ";

  /**
   * A QueryUserResponse being returned when the query failed
   */
  public static final QueryUsersResponse FAILED_REQUEST = new QueryUsersResponse(new HashSet<>(),
      null);

  /**
   * An Integer that gets deducted from the size of usernames in response to the sender
   */
  private static final int ADJUST_FOR_SENDER = 1;
  private int messageIdentifier = QUERY_USER_RESPONSE;
  private Set<String> usernames;
  private int numUsers;
  private String requestUsername;

  /**
   * @param usernames       a Set of String represents the users currently connected to the server
   * @param requestUsername a String represents the username of the client that's requesting the
   *                        information
   */
  public QueryUsersResponse(Set<String> usernames, String requestUsername) {
    this.usernames = usernames;
    this.numUsers = usernames.size();
    this.requestUsername = requestUsername;
  }

  /**
   * Empty constructor - used by the server to get a response object
   */
  public QueryUsersResponse() {
  }

  /**
   * ex. there are three clients in the server, "a", "ab", "abc", "a" requested "who", then this
   * should write as follows: 23 2 2 ab 3 abc
   *
   * @param out DataOutputStream object, used to write to output stream
   * @throws IOException exception
   */
  @Override
  public synchronized void sendResponse(DataOutputStream out, ServerThread serverThread)
      throws IOException {
    out.writeInt(messageIdentifier);
    if (this == FAILED_REQUEST) {
      return;
    }

    out.writeInt(usernames.size() - ADJUST_FOR_SENDER);
    for (String username : usernames) {
      if (username.equals(requestUsername)) {
        continue;
      }

      out.writeInt(username.length());
      out.writeUTF(username);
    }
  }

  /**
   * @param in           a DataInputStream object used to receive responses through socket
   * @param serverThread a ServerThread represents the server-client connection
   * @return a Response object used to generate a message by the server
   * @throws IOException when there is an error reading request
   */
  @Override
  public synchronized ResponseSender createResponse(DataInputStream in, ServerThread serverThread)
      throws IOException {
    QueryUsersRequest request = (QueryUsersRequest) new QueryUsersRequest().getRequest(in);

    String requestUsername = request.getUsername();
    ResponseSender response = FAILED_REQUEST;
    if (serverThread.getServer().isValidUser(requestUsername)) {
      Set<String> usernames = serverThread.getServer().getUsernames();
      response = new QueryUsersResponse(usernames, requestUsername);
    }
    return response;
  }

  /**
   * Update client's display, show a list of other users. Will not display anything if the user is
   * invalid or user is the only person in the room
   *
   * @param in           a DataInputStream represents the input stream from socket
   * @param clientThread a ClientThread object represents the
   * @return an Empty response object, with side effect of updating the client's display
   * @throws IOException when there is an exception reading response from server
   */
  @Override
  public ResponseReceiver getResponse(DataInputStream in, ClientThread clientThread)
      throws IOException {
    int numUsers = in.readInt();
    if (numUsers == 0) {
      return new QueryUsersResponse();
    }

    StringBuilder sb = new StringBuilder(RESPONSE_HEADER).append(System.lineSeparator());
    for (int i = 1; i <= numUsers; i++) {
      in.readInt();
      String username = in.readUTF();
      sb.append(i)
          .append(". ")
          .append(username)
          .append(System.lineSeparator());
    }
    ResponseReceiver response = new QueryUsersResponse();
    clientThread.getClient().updateDisplay(sb.toString());
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
    if (!(o instanceof QueryUsersResponse that)) {
      return false;
    }
    return messageIdentifier == that.messageIdentifier && numUsers == that.numUsers
        && Objects.equals(usernames, that.usernames) && Objects.equals(
        requestUsername, that.requestUsername);
  }

  /**
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(messageIdentifier, usernames, numUsers);
  }

  /**
   * @return object to string
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("QueryUsersResponse{");
    sb.append("messageIdentifier=").append(messageIdentifier);
    sb.append(", usernames=").append(usernames);
    sb.append(", numUsers=").append(numUsers);
    sb.append(", requestUsername='").append(requestUsername).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
