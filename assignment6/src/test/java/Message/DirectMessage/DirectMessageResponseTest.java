package Message.DirectMessage;

import static org.junit.jupiter.api.Assertions.*;
import Server.ServerMain;
import Client.Client;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectMessageResponseTest {
  DirectMessageResponse a;
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
    a=new DirectMessageResponse("a","b","@b asd");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests","DirectMessageResponse.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests","DirectMessageResponse.txt"));
    din = new DataInputStream(in);
    ServerMain.main(null);
    port = ServerMain.getServer().getServerPort();
    host = ServerMain.getServer().getServerHost();
    soc = new Socket(host, port);
    s = new Server();
    allUsers = new ConcurrentHashMap<String, ServerThread>();
    st = new ServerThread(s, soc);
    allUsers.put("a", st);
    s.setAllUsers(allUsers);
    ct = new ClientThread(new Client("a", host, port), soc, "a");
  }



  @Test
  void setMessageBody() {
    a.setMessageBody("asd");
    Assertions.assertEquals("asd",a.getMessageBody());
  }

  @Test
  void setMessageIdentifier() {
    a.setMessageIdentifier(25);
    Assertions.assertEquals(25,a.getMessageIdentifier());
  }



  @Test
  void testEquals() {
    DirectMessageResponse b = new DirectMessageResponse("a","b","@b asd");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    DirectMessageRequest b2 = new DirectMessageRequest("a","b","@b a");
    Assertions.assertFalse(a.equals(b2));

    assertNotEquals(a, new DirectMessageResponse("","b","@b asd"));
    assertNotEquals(a, new DirectMessageResponse("a","","@b asd"));
    assertNotEquals(a, new DirectMessageResponse("a","b","@b as"));
  }

  @Test
  void testHashCode() {
    DirectMessageResponse b = new DirectMessageResponse("a","b","@b asd");
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    DirectMessageResponse b = new DirectMessageResponse("a","b","@b asd");
    Assertions.assertEquals(b.toString(),a.toString());
  }

  @Test
  void sendResponse() throws IOException {
    assertDoesNotThrow(()->a.sendResponse(dout,st));
  }
}