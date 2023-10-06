package Message.Request;

import Client.ClientThread;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Cody Cao, Letian Shi
 */
public interface RequestSender {

  /**
   *
   * @param out a DataInputStream object used to receive requests through socket
   * @param serverThread serverThread
   * @throws IOException exception
   */
  void sendRequest (DataOutputStream out, ClientThread serverThread) throws IOException;
}
