package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MileBalanceTest {

  MileBalance mileBalance;

  @BeforeEach
  void setUp() {
    mileBalance = new MileBalance(100000, 50000, 50000);
  }

  @Test
  void getTotalMile() {
    assertEquals(100000, mileBalance.getTotalMile());
  }

  @Test
  void setTotalMile() {
    mileBalance.setTotalMile(20000);
    assertEquals(20000, mileBalance.getTotalMile());
  }

  @Test
  void getEarnedMile() {
    assertEquals(50000, mileBalance.getEarnedMile());
  }

  @Test
  void setEarnedMile() {
    mileBalance.setEarnedMile(20000);
    assertEquals(20000, mileBalance.getEarnedMile());
  }

  @Test
  void getExpiringMile() {
    assertEquals(50000, mileBalance.getExpiringMile());
  }

  @Test
  void setExpiringMile() {
    mileBalance.setExpiringMile(20000);
    assertEquals(20000, mileBalance.getExpiringMile());
  }
}