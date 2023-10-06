package Message.DirectMessage;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
import Message.BroadcastMessage.BroadcastRequest;
import Server.Server;
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
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectMessageRequestTest {
  DirectMessageRequest a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  @BeforeEach
  void setUp() throws FileNotFoundException {
    a=new DirectMessageRequest("a","b","@b asd");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "DirectMessageRequest.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "DirectMessageRequest.txt"));
    din = new DataInputStream(in);
  }

  @Test
  void getRequestFromCommand() {
    DirectMessageRequest t=DirectMessageRequest.getRequestFromCommand("a","@b asd");
    Assertions.assertEquals("asd",t.getMessage());
  }

  @Test
  void sendRequest() throws IOException {
    a.sendRequest(dout,ct);
    Assertions.assertEquals(25, din.readInt());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("a", din.readUTF());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("b", din.readUTF());
    Assertions.assertEquals(6, din.readInt());
    Assertions.assertEquals("@b asd", din.readUTF());
  }

  @Test
  void getRequest() throws IOException {
    a.sendRequest(dout,ct);
    din.readInt();
    DirectMessageRequest t=(DirectMessageRequest)a.getRequest(din);
    Assertions.assertEquals(a,t);
  }

  @Test
  void getSenderUsernameSize() {
    Assertions.assertEquals(1,a.getSenderUsernameSize());
  }

  @Test
  void getSenderUsername() {
    Assertions.assertEquals("a",a.getSenderUsername());
  }

  @Test
  void getRecipientUsernameSize() {
    Assertions.assertEquals(1,a.getRecipientUsernameSize());
  }

  @Test
  void getRecipientUsername() {
    Assertions.assertEquals("b",a.getRecipientUsername());
  }

  @Test
  void getMessageSize() {
    Assertions.assertEquals(6,a.getMessageSize());
  }

  @Test
  void getMessage() {
    Assertions.assertEquals("@b asd",a.getMessage());
  }

  @Test
  void testEquals() {
    DirectMessageRequest b = new DirectMessageRequest("a","b","@b asd");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    DirectMessageRequest b2 = new DirectMessageRequest("a","b","@b a");
    Assertions.assertFalse(a.equals(b2));

    assertNotEquals(a, new DirectMessageRequest("","b","@b asd"));
    assertNotEquals(a, new DirectMessageRequest("a","","@b asd"));
    assertNotEquals(a, new DirectMessageRequest("a","b","@b yo"));
  }

  @Test
  void testHashCode() {
    DirectMessageRequest b = new DirectMessageRequest("a","b","@b asd");
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    DirectMessageRequest b = new DirectMessageRequest("a","b","@b asd");
    Assertions.assertEquals(b.toString(),a.toString());
  }
}