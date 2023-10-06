package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * @author Cody Cao, Letian Shi
 */
public class ClientMain {

  /**
   * a String that prompts user to input host
   */
  public static final String HOST_INPUT_PROMPT = "Please input server's host:";

  /**
   * a String that prompts user to input port
   */
  public static final String PORT_INPUT_PROMPT = "Please input server's port number:";

  /**
   * a String that prompts user to input username
   */
  public static final String USERNAME_INPUT_PROMPT = "Please input username:";
  /**
   * a String that prompts error type invalid
   */
  public static final String ERROR_INVALID = "Invalid. Please enter again:";

  /**
   * Main function to start a client. The thread will ask for a username before connecting to the
   * server
   *
   * @param args not used
   */


  public static void main(String[] args) {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    String HOST = getInput(HOST_INPUT_PROMPT, input);
    int PORT = Integer.valueOf(getInput(PORT_INPUT_PROMPT, input));
    String userName = getInput(USERNAME_INPUT_PROMPT, input);

    Client client = new Client(userName, HOST, PORT);
    client.startClient(input);
  }

  private static String getInput(String prompt, BufferedReader input) {
    System.out.println(prompt);
    String userInput = null;
    while (userInput == null || userInput.equals("")) {
      // null, empty, whitespace(s) not allowed.
      try {
        while (!input.ready()) {
          Thread.sleep(Client.SLEEP_TIME);
        }

        userInput = input.readLine().trim();
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (userInput.equals("")) {
        System.out.println(ERROR_INVALID);
      }
    }
    return userInput;
  }
}
