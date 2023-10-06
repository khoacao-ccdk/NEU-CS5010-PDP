package Message.Insult;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
import Message.Connect.ConnectRequest;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InsultRequestTest {
  InsultRequest a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    a=new InsultRequest("a","b");

    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "InsultRequest.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "InsultRequest.txt"));
    din = new DataInputStream(in);
  }

  @Test
  void sendRequest() throws IOException {
    a.sendRequest(dout,ct);
    Assertions.assertEquals(27, din.readInt());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("a", din.readUTF());
    Assertions.assertEquals(1, din.readInt());
    Assertions.assertEquals("b", din.readUTF());

  }

  @Test
  void getRequest() throws IOException {
    a.sendRequest(dout,ct);
    din.readInt();
    Assertions.assertEquals(a,a.getRequest(din));
  }

  @Test
  void getSenderUsername() {
    Assertions.assertEquals("a",a.getSenderUsername());
  }

  @Test
  void getRecipientUsername() {
    Assertions.assertEquals("b",a.getRecipientUsername());
  }

  @Test
  void testEquals() {
    InsultRequest b = new InsultRequest("a","b");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    InsultRequest b2 = new InsultRequest("aa","bb");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    InsultRequest b = new InsultRequest("a","b");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    InsultRequest b = new InsultRequest("a","b");
    Assertions.assertEquals(b.toString(), a.toString());
  }
}