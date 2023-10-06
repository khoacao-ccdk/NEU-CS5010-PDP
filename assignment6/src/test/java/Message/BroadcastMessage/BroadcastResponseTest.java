package Message.BroadcastMessage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import Client.Client;
import Client.ClientThread;
import Server.ServerMain;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BroadcastResponseTest {

  BroadcastResponse a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  String host;
  int port;
  ConcurrentMap<String, ServerThread> allUsers;
  Server s;
  Socket soc;

  @BeforeEach
  void setUp() throws IOException {
    ServerMain.main(null);
    port = ServerMain.getServer().getServerPort();
    host = ServerMain.getServer().getServerHost();
    soc = new Socket(host, port);
    s = new Server();
    allUsers = new ConcurrentHashMap<String, ServerThread>();
    st = new ServerThread(s, soc);
    allUsers.put("a", st);
    s.setAllUsers(allUsers);
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "BroadcastResponse.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "BroadcastResponse.txt"));
    din = new DataInputStream(in);
    ct = new ClientThread(new Client("a", host, port), soc, "a");
    a = new BroadcastResponse(1, "a", 7, "@all aa");
  }


  @Test
  void testEquals() {
    BroadcastResponse b = new BroadcastResponse(1, "a", 7, "@all aa");
    BroadcastResponse br = new BroadcastResponse(1, "aa", 7, "@all aa");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    Assertions.assertFalse(a.equals(br));
  }

  @Test
  void testHashCode() {
    BroadcastResponse b = new BroadcastResponse(1, "a", 7, "@all aa");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    BroadcastResponse b = new BroadcastResponse(1, "a", 7, "@all aa");
    Assertions.assertEquals(b.toString(), a.toString());
  }

  @Test
  void sendResponse() throws IOException {
    assertDoesNotThrow(()->a.sendResponse(dout,st));


  }

  @Test
  void getResponse() throws IOException {

    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("s");
   assertDoesNotThrow(()-> a.getResponse(din, ct));
  }

  @Test
  void createResponse() throws IOException {
    out = new FileOutputStream(new File("tests", "ServerThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ServerThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("s");
    assertDoesNotThrow(()->a.createResponse(din,st));
  }
}