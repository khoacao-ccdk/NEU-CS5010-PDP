package Message.Disconnect;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DisconnectResponseTest {
  DisconnectResponse a;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;
  ClientThread ct;
  ServerThread st;
  ConcurrentMap<String, ServerThread> allUsers;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    a=new DisconnectResponse(true,"a");
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests", "DisconnectResponse.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests", "DisconnectResponse.txt"));
    din = new DataInputStream(in);
    allUsers=new ConcurrentHashMap<String, ServerThread>();
    Server s=new Server();
    st=new ServerThread(s,new Socket());
    allUsers.put("a",st);
    s.setAllUsers(allUsers);

  }

  @Test
  void createResponse() throws IOException {
    dout.writeInt(1);
    dout.writeUTF("a");
    DisconnectResponse t=(DisconnectResponse)a.createResponse(din,st);
    DisconnectResponse t2=new DisconnectResponse(true,"You are no longer connected.");
    Assertions.assertEquals(t,t2);
  }
}