package Client;

import static org.junit.jupiter.api.Assertions.*;

import Server.ServerMain;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {

  private Client a;
  private String host;
  private int port;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  private ClientThread ct;
  private Socket soc;

  @BeforeEach
  void setUp() throws IOException {
    ServerMain.main(null);
    host = ServerMain.getServer().getServerHost();
    port = ServerMain.getServer().getServerPort();
    a = new Client("a", host, port);
    soc = new Socket(host, port);
    ct = new ClientThread(a, soc, "a");
  }

  @Test
  void startClient() {
    BufferedReader i = new BufferedReader(new InputStreamReader(System.in));
    assertDoesNotThrow(() -> a.startClient(i));


  }


  @Test
  void updateDisplay() {
    System.setOut(new PrintStream(outContent));
    a.updateDisplay("aa");
    Assertions.assertEquals("aa\r\n", outContent.toString());
    System.setOut(originalOut);

  }

  @Test
  void closeClient() {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    assertDoesNotThrow(() -> a.startClient(input));
    a.closeClient();
    Assertions.assertEquals(ClientState.DISCONNECTED, a.getState());
  }

  @Test
  void testEquals() {
    Client b = new Client("a", host, port);
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    Client b2 = new Client("aa", host, port);
    Assertions.assertFalse(a.equals(b2));

    assertNotEquals(a, new Client("d", host, port));
    new Client("a", null, port);
    new Client("a", host, 0);
  }

  @Test
  void testHashCode() {
    Client b = new Client("a", host, port);
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    Client b = new Client("a", host, port);
    Assertions.assertEquals(b.toString(), a.toString());
  }


  @Test
  void getState() {
    Assertions.assertEquals(null, a.getState());
  }

  @Test
  void startInputThread() throws IOException {
    BufferedReader i = new BufferedReader(new InputStreamReader(System.in));
    assertDoesNotThrow(() -> a.startClient(i));
    assertDoesNotThrow(() -> a.startInputThread(ct));
  }


  @Test
  void testStartInputThread() {
    assertDoesNotThrow(() -> a.startInputThread(ct));
  }

  @Test
  void isValidInput() {
    Assertions.assertTrue(a.isValidInput("logoff"));
    Assertions.assertTrue(a.isValidInput("who"));
    Assertions.assertTrue(a.isValidInput("@all asd"));
    Assertions.assertFalse(a.isValidInput("?"));
    Assertions.assertTrue(a.isValidInput("!a"));
    assertFalse(a.isValidInput("@b"));
    assertFalse(a.isValidInput("@"));

  }
}