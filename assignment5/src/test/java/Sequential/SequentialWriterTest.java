package Sequential;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SequentialWriterTest {
private SequentialWriter a;
private String[] tt;
  @BeforeEach
  void setUp() {
    File theDir = new File("SequentialOutput");
    if (!theDir.exists()){
      theDir.mkdirs();
    }
    tt=new String[2];
    tt[0]="1";
    tt[1]="2";
    a=new SequentialWriter("","asd",tt);

  }

  @Test
  void getFilename() {
    Assertions.assertEquals("asd",a.getFilename());
  }

  @Test
  void getData() {
    Assertions.assertEquals(tt,a.getData());
  }

  @Test
  void createFile() throws IOException {
    a.createFile("");
    Path k = Files.createTempDirectory("SequentialOutput");
    Assertions.assertTrue(Files.isDirectory(k));
  }

  @Test
  void writeToFile() throws Exception {
    a.writeToFile(a.getFilename(),a.getData(),true);
    Assertions.assertTrue(new File("SequentialOutput/asd.csv").exists());
  }

  @Test
  void testEquals() {
    SequentialWriter b=new SequentialWriter("","asd",new String[]{"1","2"});
    SequentialWriter b2=new SequentialWriter("","aasd",new String[]{"1","2"});
    Assertions.assertEquals(true,a.equals(b));
    Assertions.assertEquals(true,a.equals(a));
    Assertions.assertEquals(false,a.equals("a"));
    Assertions.assertEquals(false,a.equals(""));
    Assertions.assertEquals(false,a.equals(b2));

  }

  @Test
  void testHashCode() {
    SequentialWriter b=new SequentialWriter("","asd",new String[]{"1","2"});
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    SequentialWriter b=new SequentialWriter("","asd",new String[]{"1","2"});
    Assertions.assertEquals(b.toString(),a.toString());
  }
}