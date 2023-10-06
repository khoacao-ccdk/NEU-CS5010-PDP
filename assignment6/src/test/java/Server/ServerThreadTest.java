package Server;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientMain;
import java.io.ByteArrayInputStream;
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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerThreadTest {

  ServerThread a;
  Socket soc;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  final InputStream backup = System.in;
  String host;
  int port;
  ConcurrentMap<String, ServerThread> allUsers;
  Server s;

  @BeforeEach
  void setUp() throws IOException {
    ServerMain.main(null);
    host = ServerMain.getServer().getServerHost();
    port = ServerMain.getServer().getServerPort();
    soc = new Socket(host, port);
    allUsers = new ConcurrentHashMap<String, ServerThread>();
    s = new Server();
    s.setAllUsers(allUsers);
    a = new ServerThread(s, soc);
  }

  @Test
  void getServer() {
    Assertions.assertEquals(s, a.getServer());
  }

  @Test
  void getIn() throws IOException {
    Assertions.assertEquals(null, a.getIn());
  }

  @Test
  void getOut() {
    Assertions.assertEquals(null, a.getOut());
  }

  @Test
  void getUserName() {
    a.setUserName("a");
    Assertions.assertEquals("a", a.getUserName());
  }

  @Test
  void setUserName() {
    a.setUserName("a");
    Assertions.assertEquals("a", a.getUserName());
  }

  @Test
  void createResponse() throws Exception {
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);
    dout.writeInt(19);
    dout.writeInt(1);
    dout.writeUTF("a");
    assertDoesNotThrow(() -> a.createResponse(din, dout));

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);
    dout.writeInt(21);
    dout.writeInt(1);
    dout.writeUTF("a");
    assertDoesNotThrow(() -> a.createResponse(din, dout));

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);
    dout.writeInt(25);
    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("b");
    dout.writeInt(1);
    dout.writeUTF("s");
    assertDoesNotThrow(() -> a.createResponse(din, dout));

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);
    dout.writeInt(22);
    dout.writeInt(1);
    dout.writeUTF("a");
    assertDoesNotThrow(() -> a.createResponse(din, dout));

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);
    dout.writeInt(24);
    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("b");
    assertDoesNotThrow(() -> a.createResponse(din, dout));

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);
    dout.writeInt(27);
    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("b");
    assertDoesNotThrow(() -> a.createResponse(din, dout));

  }

  @Test
  void setIn() {
    a.setIn(din);
    Assertions.assertEquals(din,a.getIn());
  }


  @Test
  void testEquals() {
    ServerThread b = new ServerThread(s, soc);
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals("a"));
    Assertions.assertFalse(a.equals(""));
    ServerThread b2 = new ServerThread(new Server(), soc);
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    ServerThread b = new ServerThread(s, soc);
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }
}