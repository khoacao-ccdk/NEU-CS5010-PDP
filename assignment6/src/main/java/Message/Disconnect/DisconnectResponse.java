package Message.Disconnect;


import Message.Connect.ConnectResponse;
import Message.Response.ResponseSender;
import Server.Server;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Cody Cao, Letian Shi
 */
public class DisconnectResponse extends ConnectResponse {

  /**
   * Send back a CONNECT_RESPONSE The success field will return 'true' if the disconnect is
   * successful (valid user) The message field is set as follows: If the disconnect was successful,
   * the message should be 'You are no longer connected.'. If the disconnect failed, a message
   * explaining.
   */

  public static final String DISCONNECT_SUCCESSFUL = "You are no longer connected.";

  /**
   * An error message returned when the user was not connected to the server from the start
   */
  public static final String DISCONNECT_FAILED = "Disconnect failed. The user was not connected from the beginning";

  /**
   * DisconnectMessageHelper
   *
   * @param in DataInputStream
   * @return ConnectMessage
   * @throws IOException exception
   */
  @Override
  public synchronized ResponseSender createResponse(DataInputStream in, ServerThread serverThread)
      throws IOException {
    DisconnectRequest request = (DisconnectRequest) new DisconnectRequest().getRequest(in);

    Server server = serverThread.getServer();
    ConcurrentMap<String, ServerThread> allUsers = server.getAllUsers();
    String username = request.getUsername();

    ResponseSender message = new DisconnectResponse(true, DisconnectResponse.DISCONNECT_SUCCESSFUL);
    if (allUsers.containsKey(username)) {
      server.removeUser(username);
    } else {
      message = new DisconnectResponse(false, DisconnectResponse.DISCONNECT_FAILED);
    }
    return message;
  }

  /**
   * @param success if disconnect is successful
   * @param message a String represents the message being sent to the client
   */
  public DisconnectResponse(boolean success, String message) {
    super(success, message);
  }

  /**
   * Constructs a new DisconnectResponse object
   */
  public DisconnectResponse() {
    super();
  }


}
