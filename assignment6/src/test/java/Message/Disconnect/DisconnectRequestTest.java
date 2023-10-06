package Message.Disconnect;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
import Message.DirectMessage.DirectMessageRequest;
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

class DisconnectRequestTest {
  DisconnectRequest a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  @BeforeEach
  void setUp() throws FileNotFoundException {
    a=new DisconnectRequest("a");

    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "DisconnectRequest.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "DisconnectRequest.txt"));
    din = new DataInputStream(in);
  }

  @Test
  void sendRequest() throws IOException {
    a.sendRequest(dout,ct);
    Assertions.assertEquals(21, din.readInt());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("a", din.readUTF());

  }

  @Test
  void getRequest() throws IOException {
    a.sendRequest(dout,ct);
    din.readInt();
    DisconnectRequest t=(DisconnectRequest) a.getRequest(din);
    Assertions.assertEquals(t,a);
  }

  @Test
  void getUsername() {
    Assertions.assertEquals("a",a.getUsername());
  }

  @Test
  void testEquals() {
    DisconnectRequest b = new DisconnectRequest("a");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    DisconnectRequest b2 = new DisconnectRequest("aa");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    DisconnectRequest b = new DisconnectRequest("a");
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    DisconnectRequest b = new DisconnectRequest("a");
    Assertions.assertEquals(b.toString(),a.toString());
  }
}