package problem1;

/**
 * Represents a Frequent Flyer account
 *
 * @author Cody Cao
 */
public class FrequentFlyer {

  private String id;
  private Name name;
  private String email;
  private MileBalance mileBalance;

  private FrequentFlyerDirectory directory;

  /**
   * Creates a new FrequentFlyer object that represents a frequent flyer's account information
   *
   * @param id          a String with length of 12 represents the account id
   * @param name        a Name object that holds information of the frequent flyer's name
   * @param email       a String represents the frequent flyer's email
   * @param mileBalance a MileBalance object represents the frequent flyer's mileage balance
   *                    information
   */
  public FrequentFlyer(String id, Name name, String email, MileBalance mileBalance) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.mileBalance = mileBalance;
    this.directory = null;
  }

  /**
   * @return a String represents FrequentFlyer's account id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @return a Name object represents FrequentFlyer's name
   */
  public Name getName() {
    return this.name;
  }

  /**
   * @return a String represents FrequentFlyer's email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @return a MileBalance object represents FrequentFlyer's mileage balance
   */
  public MileBalance getMileBalance() {
    return this.mileBalance;
  }

  /**
   * Set FrequentFlyer's new email address
   *
   * @param email a String represents FrequentFlyer's email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  public void setDirectory(FrequentFlyerDirectory directory) {
    this.directory = directory;
  }

  /**
   * Transfer a FrequentFlyer's miles to another FrequentFlyer's account
   *
   * @param deposit a Deposit object with information of the receiver and the number of mileage
   *                being transferred
   */
  public void transferMile(Deposit deposit) {
    if (deposit == null) {
      return;
    }
    //Get deposit information
    String receiverId = deposit.getReceiverId();
    Name receiverName = deposit.getReveiverName();
    int transferAmount = deposit.getAmount();

    //Verify that the information matches an account
    FrequentFlyer receiver = directory.verifyFlyerInfo(receiverId, receiverName);
    if (receiver == null) {
      return;
    }

    //Verify that the amount of mile being transferred is valid
    if (transferAmount < Deposit.AMOUNT_LOWER_LIMIT ||
        transferAmount > Deposit.AMOUNT_UPPER_LIMIT ||
        transferAmount > this.mileBalance.getTotalMile()) {
      return;
    }

    //Perform the transfer
    this.mileBalance.setTotalMile(this.mileBalance.getTotalMile() - transferAmount);
    this.mileBalance.setExpiringMile(
        Math.max(this.mileBalance.getExpiringMile() - transferAmount, 0));

    MileBalance receiverMileBalance = receiver.getMileBalance();
    receiverMileBalance.setTotalMile(receiverMileBalance.getTotalMile() + transferAmount);
    receiverMileBalance.setEarnedMile(receiverMileBalance.getEarnedMile() + transferAmount);
    receiverMileBalance.setExpiringMile(receiverMileBalance.getExpiringMile() + transferAmount);
  }
}
