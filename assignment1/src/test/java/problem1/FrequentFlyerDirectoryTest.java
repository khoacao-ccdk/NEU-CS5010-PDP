package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrequentFlyerDirectoryTest {

  FrequentFlyerDirectory directory;
  FrequentFlyer flyer1, flyer2;

  @BeforeEach
  void setUp() {
    directory = new FrequentFlyerDirectory();
    flyer1 = new FrequentFlyer("123456789012",
        new Name("Cody", "", "Cao"),
        "test1@gmail.com",
        new MileBalance(20000, 5000, 10000)
    );

    flyer2 = new FrequentFlyer("098765432109",
        new Name("John", "C", "Doe"),
        "test2@gmail.com",
        new MileBalance(10000, 5000, 5000)
    );
    directory.addNewFlyer(flyer1);
    directory.addNewFlyer(flyer2);
  }

  @Test
  void verifyFlyerInfo() {
    assertSame(flyer1, this.directory.verifyFlyerInfo("123456789012",
        new Name("Cody", "", "Cao")));

    assertSame(flyer2, this.directory.verifyFlyerInfo("098765432109",
        new Name("John", "C", "Doe")));
  }

  @Test
  void verifyFlyerInfoIncorrectInfo() {
    //Incorrect id
    assertSame(null, this.directory.verifyFlyerInfo("234523452345",
        new Name("Cody", "", "Cao")));

    //Correct id, incorrect name
    assertSame(null, this.directory.verifyFlyerInfo("098765432109",
        new Name("Incorrect", "C", "Name")));
  }
}