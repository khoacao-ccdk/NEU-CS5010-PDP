package Message.BroadcastMessage;

import static org.junit.jupiter.api.Assertions.*;

import Client.Client;
import Client.ClientThread;
import Message.Request.RequestReceiver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.crypto.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BroadcastRequestTest {

  BroadcastRequest a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    a = new BroadcastRequest("a", "@all aa");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "BroadcastRequest.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "BroadcastRequest.txt"));
    din = new DataInputStream(in);
    ct = new ClientThread(new Client("a", null, 5555), new Socket(), "a");
  }

  @Test
  void getRequestFromCommand() {
    Assertions.assertEquals("asd", a.getRequestFromCommand("b", "@all asd").getMessage());

  }

  @Test
  void sendRequest() throws IOException {
    a.sendRequest(dout, ct);
    Assertions.assertEquals(24, din.readInt());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("a", din.readUTF());
    Assertions.assertEquals(7, din.readInt());
    Assertions.assertEquals("@all aa", din.readUTF());
  }

  @Test
  void getSenderUsernameSize() {
    Assertions.assertEquals(1, a.getSenderUsernameSize());
  }

  @Test
  void getSenderUsername() {
    Assertions.assertEquals("a", a.getSenderUsername());
  }

  @Test
  void getMessageSize() {
    Assertions.assertEquals(7, a.getMessageSize());
  }

  @Test
  void getMessage() {
    Assertions.assertEquals("@all aa", a.getMessage());
  }

  @Test
  void testEquals() {
    BroadcastRequest b = new BroadcastRequest("a", "@all aa");
    BroadcastRequest bf = new BroadcastRequest("aaa", "@all aa");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    Assertions.assertFalse(a.equals(bf));
  }

  @Test
  void testHashCode() {
    BroadcastRequest b = new BroadcastRequest("a", "@all aa");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    BroadcastRequest b = new BroadcastRequest("a", "@all aa");
    Assertions.assertEquals(b.toString(), a.toString());
  }
}