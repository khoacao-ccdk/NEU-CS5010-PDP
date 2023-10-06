package Message.Response;

import Server.ServerThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * ResponseSender Interface - used by the client to send request through socket
 */
public interface ResponseSender {

  /**
   * Send response from server to clients
   *
   * @param out          a DataOutputStream object used to send responses through socket
   * @param serverThread serverThread
   * @throws IOException when there is an exception sending information through socket
   */
  void sendResponse(DataOutputStream out, ServerThread serverThread) throws IOException;

  /**
   * @param in           a DataInputStream object used to receive responses through socket
   * @param serverThread serverThread
   * @return ResponseSender
   * @throws IOException when there is an exception reading from socket
   */
  ResponseSender createResponse(DataInputStream in, ServerThread serverThread) throws IOException;
}
