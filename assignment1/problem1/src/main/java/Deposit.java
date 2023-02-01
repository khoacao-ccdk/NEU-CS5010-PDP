/**
 * Represents a mile deposit transaction
 *
 * @author Cody Cao
 */
public class Deposit {

  public static final int AMOUNT_LOWER_LIMIT = 1000;
  public static final int AMOUNT_UPPER_LIMIT = 10000;
  private int amount;
  private String receiverId;
  private Name reveiverName;

  /**
   * Creates a new Deposit object represents the mileage deposit transaction
   *
   * @param amount       an int represents the number of mileage being transferred (within the range
   *                     of AMOUNT_L0WER_LIMIT and AMOUNT_UPPER_LIMIT)
   * @param receiverId   a String represents the receiver account id
   * @param receiverName a String represents the receiver name
   */
  public Deposit(int amount, String receiverId, Name receiverName) {
    this.amount = amount;
    this.receiverId = receiverId;
    this.reveiverName = receiverName;
  }

  /**
   * @return the number of miles being transferred to another FrequentFlyer account
   */
  public int getAmount() {
    return this.amount;
  }

  /**
   * @return a String represents another FrequentFlyer's account id
   */
  public String getReceiverId() {
    return this.receiverId;
  }

  /**
   * @return a Name object represents another FrequentFlyer's name
   */
  public Name getReveiverName() {
    return this.reveiverName;
  }
}
