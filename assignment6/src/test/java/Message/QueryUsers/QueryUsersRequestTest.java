package Message.QueryUsers;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientThread;
import Message.Insult.InsultRequest;
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

class QueryUsersRequestTest {
  QueryUsersRequest a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  @BeforeEach
  void setUp() throws FileNotFoundException {
    a=new QueryUsersRequest("a");

    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "QueryUsersRequest.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "QueryUsersRequest.txt"));
    din = new DataInputStream(in);
  }

  @Test
  void sendRequest() throws IOException {
    a.sendRequest(dout,ct);
    din.readInt();
    Assertions.assertEquals(a,a.getRequest(din));
  }

  @Test
  void getRequest() throws IOException {
    a.sendRequest(dout,ct);
    din.readInt();
    Assertions.assertEquals(a,a.getRequest(din));
  }

  @Test
  void getUsername() {
    Assertions.assertEquals("a",a.getUsername());
  }

  @Test
  void testEquals() {
    QueryUsersRequest b = new QueryUsersRequest("a");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    QueryUsersRequest b2 = new QueryUsersRequest("aa");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    QueryUsersRequest b = new QueryUsersRequest("a");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    QueryUsersRequest b = new QueryUsersRequest("a");
    Assertions.assertEquals(b.toString(), a.toString());
  }
}