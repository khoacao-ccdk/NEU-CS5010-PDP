package Message.QueryUsers;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
import Message.Insult.InsultResponse;
import Message.Response.ResponseReceiver;
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
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueryUsersResponseTest {
  QueryUsersResponse a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  ConcurrentMap<String, ServerThread> allUsers;
  Set<String> usernames;
  @BeforeEach
  void setUp() throws FileNotFoundException {
    usernames=new HashSet<>();
    allUsers=new ConcurrentHashMap<>();

    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "QueryUsersResponse.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "QueryUsersResponse.txt"));
    din = new DataInputStream(in);
    Server s=new Server();
    st=new ServerThread(s,new Socket());
    allUsers.put("a",st);
    allUsers.put("b",st);
    s.setAllUsers(allUsers);
    usernames=allUsers.keySet();
    a=new QueryUsersResponse(usernames,"a");
  }

  @Test
  void sendResponse() throws IOException {
    a.sendResponse(dout,st);
    Assertions.assertEquals(23,din.readInt());
    Assertions.assertEquals(1,din.readInt());
    Assertions.assertEquals(1,din.readInt());
    Assertions.assertEquals("b",din.readUTF());
  }

  @Test
  void createResponse() throws IOException {
    dout.writeInt(1);
    dout.writeUTF("a");
    a.createResponse(din,st).sendResponse(dout,st);
    Assertions.assertEquals(23,din.readInt());
    Assertions.assertEquals(1,din.readInt());
    Assertions.assertEquals(1,din.readInt());
    Assertions.assertEquals("b",din.readUTF());
  }


  @Test
  void testEquals() {
    QueryUsersResponse b=new QueryUsersResponse(usernames,"a");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    QueryUsersResponse b2=new QueryUsersResponse(usernames,"b");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    QueryUsersResponse b=new QueryUsersResponse(usernames,"a");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    QueryUsersResponse b=new QueryUsersResponse(usernames,"a");
    Assertions.assertEquals(b.toString(), a.toString());
  }
}