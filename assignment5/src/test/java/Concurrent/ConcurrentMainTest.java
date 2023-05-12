package Concurrent;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcurrentMainTest {

  @Test
  void main() {
    ConcurrentMain.main(new String[]{"10000"});

    File file = new File("ConcurrentResult", "activity-10000.csv");
    assertTrue(file.exists());
  }
}