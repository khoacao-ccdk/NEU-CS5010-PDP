package Message.Response;

import Client.ClientThread;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * ResponseReceiver interface - used by the client to retrieve response from socket
 *
 * @author Cody Cao, Letian Shi
 */
public interface ResponseReceiver {

  /**
   * For client use - receives response from server. Update the client's display if needed
   *
   * @param in           a DataInputStream represents the input stream from socket
   * @param clientThread a ClientThread object represents the
   * @return a Response object represents the response from the server
   * @throws IOException when there is an Exception when reading response
   */
  ResponseReceiver getResponse(DataInputStream in, ClientThread clientThread) throws IOException;

}
