package Server;

import static org.junit.jupiter.api.Assertions.*;

import Message.FailedMessage;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerTest {

  Server a;
  ServerThread st;
  ConcurrentMap<String, ServerThread> allUsers;

  @BeforeEach
  void setUp() throws IOException {
    allUsers = new ConcurrentHashMap<String, ServerThread>();
    a = new Server();
    st = new ServerThread(a, new Socket());
    allUsers.put("a", st);
    a.setAllUsers(allUsers);
  }

  @Test
  void startServer() {
    assertDoesNotThrow(() -> a.startServer());
  }

  @Test
  void isValidUser() {
    Assertions.assertTrue(a.isValidUser("a"));
  }

  @Test
  void getUsernames() {
    Set<String> t = new HashSet<>();
    t.add("a");
    Assertions.assertEquals(t, a.getUsernames());
  }

  @Test
  void stopServer() {
    a.startServer();
    a.stopServer();
    Assertions.assertEquals(ServerState.TERMINATED, a.getState());
  }

  @Test
  void addUser() {
    a.addUser("b", st);
    Assertions.assertEquals(2, a.getUsernames().size());
  }

  @Test
  void removeUser() {
    a.addUser("b", st);
    a.removeUser("a");
    Assertions.assertEquals(1, a.getUsernames().size());
    a.addUser("a", st);
    a.removeUser(st);
    Assertions.assertEquals(1, a.getUsernames().size());
  }

  @Test
  void getAllUsers() {
    Assertions.assertEquals(allUsers, a.getAllUsers());
  }

  @Test
  void setAllUsers() {
    a.setAllUsers(new ConcurrentHashMap<String, ServerThread>());
    Assertions.assertEquals(0, a.getAllUsers().size());
  }

  @Test
  void getServerHost() {
    Assertions.assertEquals(null, a.getServerHost());
  }

  @Test
  void getServerPort() {
    Assertions.assertEquals(0, a.getServerPort());
  }

  @Test
  void getState() {
    Assertions.assertEquals(null, a.getState());
  }

  @Test
  void testEquals() {
    Server b = new Server();
    b.setAllUsers(allUsers);
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals("a"));
    Assertions.assertFalse(a.equals(""));
    Server b2 = new Server();
    Assertions.assertFalse(a.equals(b2));

  }




}