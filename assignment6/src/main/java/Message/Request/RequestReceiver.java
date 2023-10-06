package Message.Request;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * RequestReceiver interface - used by the server to read request from socket
 *
 * @author Cody Cao, Letian Shi
 */
public interface RequestReceiver {

  /**
   * @param in DataInputStream
   * @return RequestReceiver
   * @throws IOException IOException
   */
  RequestReceiver getRequest(DataInputStream in) throws IOException;
}
