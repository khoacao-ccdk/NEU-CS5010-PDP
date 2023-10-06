package Client;

import static org.junit.jupiter.api.Assertions.*;

import Server.ServerMain;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientMainTest {
  private InputStream backup;
  private String inputStr;
  private ByteArrayInputStream in;
  @BeforeEach
  void setUp() {
    backup = System.in;
    ServerMain.main(null);
    inputStr=String.valueOf(ServerMain.getServer().getServerHost())+"\r\n"+String.valueOf(ServerMain.getServer().getServerPort())+
        "\r\n"+"Alice";
    in = new ByteArrayInputStream(inputStr.getBytes());
  }

  @Test
  void main() {
    System.setIn(in);
    assertDoesNotThrow(()->ClientMain.main(null));
    System.setIn(backup);
  }


}