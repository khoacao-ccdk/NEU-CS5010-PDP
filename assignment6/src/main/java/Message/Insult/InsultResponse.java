package Message.Insult;

import static Message.MessageIdentifier.SEND_INSULT;

import Message.DirectMessage.DirectMessageResponse;
import Message.FailedMessage;
import Message.Response.ResponseSender;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

/**
 * InsultResponse class - used to generate a random insult and send it to recipient
 *
 * @author Cody Cao, Letian Shi
 */
public class InsultResponse extends DirectMessageResponse {

  private int messageIdentifier = SEND_INSULT;

  /**
   * @param senderUsername   a String represents the insult's sender username
   * @param receiverUsername a String represents the insult's recipient username
   */
  public InsultResponse(String senderUsername, String receiverUsername) {
    super(senderUsername, receiverUsername, null);
    try {
      String insultMessage = new InsultGenerator().getInsult();
      super.setMessageIdentifier(messageIdentifier);
      super.setMessageBody(insultMessage);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Creates an empty response object - used to create a response
   */
  public InsultResponse() {
    super();
  }

  /**
   * @param in           a DataInputStream object used to receive responses through socket
   * @param serverThread a ServerThread object represents a server-client connection
   * @return a Response object represents either a FailedMessage when there's a problem sending
   * direct message or a DirectMessageResponse that will be sent to both sender and receiver
   * @throws IOException when there is an error reading from DataInputStream
   */
  @Override
  public synchronized ResponseSender createResponse(DataInputStream in, ServerThread serverThread)
      throws IOException {
    InsultRequest request = (InsultRequest) new InsultRequest().getRequest(in);

    ConcurrentMap<String, ServerThread> allUser = serverThread
        .getServer()
        .getAllUsers();

    String senderUsername = request.getSenderUsername();
    String recipientUsername = request.getRecipientUsername();

    ResponseSender message = new InsultResponse(senderUsername, recipientUsername);

    if (!allUser.containsKey(senderUsername)) {
      message = new FailedMessage(FailedMessage.FAILED_SENDER_NOT_IN_SERVER);
    }

    if (!allUser.containsKey(recipientUsername)) {
      message = new FailedMessage(FailedMessage.FAILED_RECEIVER_NOT_IN_SERVER);
    }
    return message;
  }
}
