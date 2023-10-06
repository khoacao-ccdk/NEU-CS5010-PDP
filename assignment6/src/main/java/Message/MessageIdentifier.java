package Message;

import java.io.DataInputStream;

/**
 * MessageIdentifier class - used to keep identifier values
 *
 * @author Cody Cao, Letian Shi
 */
public final class MessageIdentifier {

  /**
   * An Integer denotes the connect request from clients to server
   */
  public static final int CONNECT_MESSAGE = 19;

  /**
   * An Integer denotes connect response from server to clients
   */
  public static final int CONNECT_RESPONSE = 20;

  /**
   * An Integer denotes the disconnect request from clients to server
   */
  public static final int DISCONNECT_MESSAGE = 21;

  /**
   * An Integer denotes the request for connected users from clients to server
   */
  public static final int QUERY_CONNECTED_USERS = 22;

  /**
   * An Integer denotes the connected users response message from server to clients
   */
  public static final int QUERY_USER_RESPONSE = 23;

  /**
   * An Integer denotes the request for sending a broadcast message from clients to server
   */
  public static final int BROADCAST_MESSAGE = 24;

  /**
   * An Integer denotes the request for sending direct message from clients to server
   */
  public static final int DIRECT_MESSAGE = 25;

  /**
   * An Integer denotes the failed response when sending a message from server to clients
   */
  public static final int FAILED_MESSAGE = 26;

  /**
   * An Integer denotes the request for sending an insult message from clients to server
   */
  public static final int SEND_INSULT = 27;


  private MessageIdentifier() {

  }


}
