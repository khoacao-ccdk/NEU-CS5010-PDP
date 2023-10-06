package Server;

import static org.junit.jupiter.api.Assertions.*;

import Client.ClientMain;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServerMainTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  DataInputStream din;
  InputStream in;
  DataOutputStream dout;
  OutputStream out;

  @BeforeEach
  void setUp() throws FileNotFoundException {
    try {
      Files.createDirectories(Path.of("tests"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    out = new FileOutputStream(new File("tests","ServerMain.txt"));
    dout = new DataOutputStream(out);
    in = new FileInputStream(new File("tests","ServerMain.txt"));
    din = new DataInputStream(in);
  }

  @Test
  void main() throws IOException {
//    InputStream backup = System.in;
//    ServerMain.main(null);
//    String inputStr=String.valueOf(ServerMain.getServer().getServerHost())+"\r\n"+String.valueOf(ServerMain.getServer().getServerPort())+
//        "\r\n"+"Alice";
//    ByteArrayInputStream in = new ByteArrayInputStream(inputStr.getBytes());
//    System.setIn(in);
//    ClientMain.main(null);
//    System.setIn(backup);
//    inputStr=String.valueOf(ServerMain.getServer().getServerHost())+"\r\n"+String.valueOf(ServerMain.getServer().getServerPort())+
//        "\r\n"+"Bill";
//    in = new ByteArrayInputStream(inputStr.getBytes());
//    System.setIn(in);
//    ClientMain.main(null);
//    System.setIn(backup);
    assertDoesNotThrow(() -> ServerMain.main(null));

  }


}