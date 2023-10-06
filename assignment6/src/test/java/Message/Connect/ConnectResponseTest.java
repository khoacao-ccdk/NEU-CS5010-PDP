package Message.Connect;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
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

class ConnectResponseTest {
  ConnectResponse a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  @BeforeEach
  void setUp() throws FileNotFoundException {
    a=new ConnectResponse(true,"asd");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    out = new FileOutputStream(new File("tests","ConnectedResponse.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests","ConnectedResponse.txt"));
    din = new DataInputStream(in);
    st=new ServerThread(new Server(),new Socket());
  }

  @Test
  void setUserName() {
    a.setUserName("aa");
    Assertions.assertEquals("aa",a.getUserName());
  }

  @Test
  void getUserName() {
    a.setUserName("aa");
    Assertions.assertEquals("aa",a.getUserName());
  }

  @Test
  void isSuccess() {
    Assertions.assertTrue(a.isSuccess());
  }

  @Test
  void sendResponse() throws IOException {
    a.sendResponse(dout,st);
    Assertions.assertEquals(20, din.readInt());
    Assertions.assertEquals(true, din.readBoolean());
    Assertions.assertEquals(3, din.readInt());
    Assertions.assertEquals("asd", din.readUTF());
  }



  @Test
  void testEquals() {
    ConnectResponse b = new ConnectResponse(true,"asd");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    ConnectResponse b2 = new ConnectResponse(false,"asd");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    ConnectResponse b = new ConnectResponse(true,"asd");
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    ConnectResponse b = new ConnectResponse(true,"asd");
    Assertions.assertEquals(b.toString(),a.toString());
  }
}