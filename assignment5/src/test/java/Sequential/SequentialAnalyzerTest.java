package Sequential;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SequentialAnalyzerTest {

  @BeforeEach
  void setUp() {
    File theDir = new File("SequentialOutput");
    if (!theDir.exists()){
      theDir.mkdirs();
    }
    SequentialAnalyzer.setInputPath("courses.csv","studentVleSmall.csv");
  }

  @Test
  void main() throws Exception {
    SequentialAnalyzer.main(new String[0]);
    Assertions.assertTrue(new File("SequentialOutput/AAA_2013J.csv").exists());
  }

  @Test
  void setInputPath() {
    SequentialAnalyzer.setInputPath("a","b");
    Assertions.assertEquals("a",SequentialAnalyzer.getCoursePath());
    Assertions.assertEquals("b",SequentialAnalyzer.getClickPath());
  }

  @Test
  void getCoursePath() {
    Assertions.assertEquals("courses.csv",SequentialAnalyzer.getCoursePath());
  }

  @Test
  void getClickPath() {
    Assertions.assertEquals("studentVleSmall.csv",SequentialAnalyzer.getClickPath());
  }
}