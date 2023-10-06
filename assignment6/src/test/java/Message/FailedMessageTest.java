package Message;


import Client.Client;
import Client.ClientThread;
import Server.Server;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FailedMessageTest {

  FailedMessage a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;

  @BeforeEach
  void setUp() throws IOException {

    a = new FailedMessage("asd");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    out = new FileOutputStream(new File("tests","FailedMsg.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests","FailedMsg.txt"));
    din = new DataInputStream(in);
    ct = new ClientThread(new Client("a", "asd", 100), new Socket(), "a");
    st = new ServerThread(new Server(), new Socket());
  }

  @Test
  void sendResponse() throws IOException {
    a.sendResponse(dout, st);
    Assertions.assertEquals(26, din.readInt());
    Assertions.assertEquals(3, din.readInt());
    Assertions.assertEquals("asd", din.readUTF());

  }

  @Test
  void createResponse() throws IOException {
    Assertions.assertNull(a.createResponse(din, st));
  }


  @Test
  void testEquals() {
    FailedMessage b = new FailedMessage("asd");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals("a"));
    Assertions.assertFalse(a.equals(""));
    FailedMessage b2 = new FailedMessage("asda");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    FailedMessage b = new FailedMessage("asd");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    FailedMessage b = new FailedMessage("asd");
    Assertions.assertEquals(b.toString(), a.toString());
  }
}