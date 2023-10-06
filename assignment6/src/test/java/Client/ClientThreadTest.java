package Client;

import static org.junit.jupiter.api.Assertions.*;

import Server.Server;
import Server.ServerMain;
import Server.ServerThread;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientThreadTest {

  ServerThread st;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ConcurrentMap<String, ServerThread> allUsers;
  Server s;
  private ClientThread a;
  private Client c;
  private String host;
  private int port;
  private Socket soc;


  @BeforeEach
  void setUp() throws IOException {
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ServerMain.main(null);
    host = ServerMain.getServer().getServerHost();
    port = ServerMain.getServer().getServerPort();
    c = new Client("a", host, port);
    soc = new Socket(host, port);
    a = new ClientThread(c, soc, "a");
    allUsers = new ConcurrentHashMap<String, ServerThread>();
    st = new ServerThread(s, soc);
    allUsers.put("a",st);
    allUsers.put("b",st);
    s = new Server();
    s.setAllUsers(allUsers);
  }

  @Test
  void addMessage() {
    a.addMessage("asd");
    Assertions.assertEquals(1, a.getMessagesToSend().size());
  }

  @Test
  void getClient() {
    Assertions.assertEquals(c, a.getClient());
  }


  @Test
  void testEquals() {
    ClientThread b = new ClientThread(c, soc, "a");
    Assertions.assertTrue(a.equals(b));
    Assertions.assertTrue(a.equals(a));
    Assertions.assertFalse(a.equals(""));
    Assertions.assertFalse(a.equals("a"));
    ClientThread b2 = new ClientThread(c, soc, "aa");
    Assertions.assertFalse(a.equals(b2));
  }

  @Test
  void testHashCode() {
    ClientThread b = new ClientThread(c, soc, "a");
    Assertions.assertEquals(b.hashCode(), a.hashCode());
  }

  @Test
  void getMessagesToSend() {
    ArrayList<String> t = new ArrayList<>();
    Assertions.assertEquals(t, a.getMessagesToSend());
  }


  @Test
  void sendConnectMessage() throws IOException {

    out = new FileOutputStream(new File("tests","ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests","ClientThread.txt"));
    din = new DataInputStream(in);

    a.setUsername("a");
    dout.writeInt(20);
    dout.writeBoolean(true);
    dout.writeInt(2);
    dout.writeUTF("aa");

    assertDoesNotThrow(() -> a.sendConnectMessage(din, dout));
  }

  @Test
  void sendDisconnectMessage() throws IOException {
    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    a.setUsername("a");
    dout.writeInt(20);
    dout.writeBoolean(true);
    dout.writeInt(2);
    dout.writeUTF("aa");

    assertDoesNotThrow(() -> a.sendDisconnectMessage(din, dout));
  }

  @Test
  void sendRequest() throws IOException {
    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    a.setUsername("a");
    dout.writeInt(20);
    dout.writeBoolean(true);
    dout.writeInt(2);
    dout.writeUTF("aa");

    assertDoesNotThrow(() -> a.sendRequest(din, dout, "logoff"));

    assertDoesNotThrow(() -> a.sendRequest(din, dout, "who"));

    assertDoesNotThrow(() -> a.sendRequest(din, dout, "@all asd"));

    assertDoesNotThrow(() -> a.sendRequest(din, dout, "@b a"));

    assertDoesNotThrow(() -> a.sendRequest(din, dout, "!b"));
  }

  @Test
  void consumeResponse() throws IOException {
    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(25);
    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("b");
    dout.writeInt(2);
    dout.writeUTF("aa");

    assertDoesNotThrow(() -> a.consumeResponse(din));

    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(27);
    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("b");
    dout.writeInt(2);
    dout.writeUTF("aa");

    assertDoesNotThrow(() -> a.consumeResponse(din));

    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(24);
    dout.writeInt(1);
    dout.writeUTF("a");
    dout.writeInt(1);
    dout.writeUTF("b");

    assertDoesNotThrow(() -> a.consumeResponse(din));

    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(23);
    dout.writeInt(2);


    assertDoesNotThrow(() -> a.consumeResponse(din));

    out = new FileOutputStream(new File("tests", "ClientThread.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "ClientThread.txt"));
    din = new DataInputStream(in);

    dout.writeInt(26);
    dout.writeInt(1);
    dout.writeUTF("a");


    assertDoesNotThrow(() -> a.consumeResponse(din));



  }


}