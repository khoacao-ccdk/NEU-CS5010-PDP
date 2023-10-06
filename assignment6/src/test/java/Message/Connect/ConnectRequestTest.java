package Message.Connect;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
import Server.ServerThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectRequestTest {

  ConnectRequest a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    a = new ConnectRequest("a");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests","ConnectedRequest.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests","ConnectedRequest.txt"));
    din = new DataInputStream(in);
  }

  @Test
  void sendRequest() throws IOException {
    a.sendRequest(dout, ct);
    Assertions.assertEquals(19, din.readInt());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("a", din.readUTF());
  }

  @Test
  void testEquals() {
    ConnectRequest b = new ConnectRequest("a");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    ConnectRequest b2 = new ConnectRequest("ab");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    ConnectRequest b = new ConnectRequest("a");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    ConnectRequest b = new ConnectRequest("a");
    Assertions.assertEquals(b.toString(), a.toString());
  }

  @Test
  void getRequest() throws IOException {
    Assertions.assertNull(a.getRequest(din));
  }
}