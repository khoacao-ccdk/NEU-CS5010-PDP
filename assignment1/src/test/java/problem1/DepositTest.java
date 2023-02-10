package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepositTest {

  Deposit deposit;

  @BeforeEach
  void setUp() {
    deposit = new Deposit(1000,
        "098765432109",
        new Name("John", "C", "Doe")
    );
  }

  @Test
  void getAmount() {
    assertEquals(1000, deposit.getAmount());
  }

  @Test
  void getReceiverId() {
    assertSame("098765432109", deposit.getReceiverId());
  }

  @Test
  void getReceiverName() {
    assertEquals(new Name("John", "C", "Doe"), deposit.getReveiverName());
  }
}