package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrequentFlyerTest {

  FrequentFlyerDirectory directory;
  FrequentFlyer giver, receiver;
  String correctReceiverId, incorrectReceiverId;
  Name correctReceiverName, incorrectReceiverName;

  @BeforeEach
  void setUp() {
    //Correct Information
    correctReceiverId = "098765432109";
    correctReceiverName = new Name("John", "C", "Doe");

    //IncorrectInformation
    incorrectReceiverId = "523453245234";
    incorrectReceiverName = new Name("Incorrect", "C", "Name");

    directory = new FrequentFlyerDirectory();
    giver = new FrequentFlyer("123456789012",
        new Name("Cody", "", "Cao"),
        "test1@gmail.com",
        new MileBalance(20000, 5000, 10000)
    );

    receiver = new FrequentFlyer(correctReceiverId,
        correctReceiverName,
        "test2@gmail.com",
        new MileBalance(10000, 5000, 5000)
    );
    directory.addNewFlyer(giver);
    directory.addNewFlyer(receiver);
  }

  @Test
  void getId() {
    assertSame("123456789012", giver.getId());
  }

  @Test
  void getEmail() {
    assertSame("test1@gmail.com", giver.getEmail());
  }

  @Test
  void setEmail() {
    giver.setEmail("test3@gmail.com");
    assertEquals("test3@gmail.com", giver.getEmail());
  }

  @Test
  void testTransferMileCorrectInfo() {
    Deposit deposit = new Deposit(5000, correctReceiverId, correctReceiverName);
    giver.transferMile(deposit);
    MileBalance giverMileBalance = giver.getMileBalance(),
        receiverMileBalance = receiver.getMileBalance();
    assertEquals(15000, giverMileBalance.getTotalMile());
    assertEquals(5000, giverMileBalance.getExpiringMile());
    assertEquals(15000, receiverMileBalance.getTotalMile());
    assertEquals(10000, receiverMileBalance.getEarnedMile());
    assertEquals(10000, receiverMileBalance.getExpiringMile());
  }

  @Test
  void testTransferMileIncorrectId() {
    Deposit deposit = new Deposit(5000, incorrectReceiverId, correctReceiverName);
    giver.transferMile(deposit);
    MileBalance giverMileBalance = giver.getMileBalance(),
        receiverMileBalance = receiver.getMileBalance();
    assertEquals(20000, giverMileBalance.getTotalMile());
    assertEquals(10000, giverMileBalance.getExpiringMile());
    assertEquals(10000, receiverMileBalance.getTotalMile());
    assertEquals(5000, receiverMileBalance.getEarnedMile());
    assertEquals(5000, receiverMileBalance.getExpiringMile());
  }

  @Test
  void testTransferMileIncorrectName() {
    Deposit deposit = new Deposit(5000, correctReceiverId, incorrectReceiverName);
    giver.transferMile(deposit);
    MileBalance giverMileBalance = giver.getMileBalance(),
        receiverMileBalance = receiver.getMileBalance();
    assertEquals(20000, giverMileBalance.getTotalMile());
    assertEquals(10000, giverMileBalance.getExpiringMile());
    assertEquals(10000, receiverMileBalance.getTotalMile());
    assertEquals(5000, receiverMileBalance.getEarnedMile());
    assertEquals(5000, receiverMileBalance.getExpiringMile());
  }

  @Test
  void testTransferMileInvalidAmount() {
    Deposit deposit = new Deposit(15000, correctReceiverId, correctReceiverName);
    giver.transferMile(deposit);
    MileBalance giverMileBalance = giver.getMileBalance(),
        receiverMileBalance = receiver.getMileBalance();
    assertEquals(20000, giverMileBalance.getTotalMile());
    assertEquals(10000, giverMileBalance.getExpiringMile());
    assertEquals(10000, receiverMileBalance.getTotalMile());
    assertEquals(5000, receiverMileBalance.getEarnedMile());
    assertEquals(5000, receiverMileBalance.getExpiringMile());
  }
}