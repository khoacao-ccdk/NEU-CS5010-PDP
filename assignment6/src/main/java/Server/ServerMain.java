package Server;

/**
 * Main class to start a server. The server will find an available port and print out which port it
 * is listening on
 *
 * @author Cody Cao, Letian Shi
 */
public class ServerMain {

  /**
   * a Server object that will be instantiated when the server starts
   */
  private static Server server;

  /**
   * Starts a server running on localhost, port as defined by the constant port value
   *
   * @param args not used
   */
  public static void main(String[] args) {
    server = new Server();
    server.startServer();
  }

  /**
   * @return a Server object represents the server
   */
  public static Server getServer() {
    return server;
  }
}
