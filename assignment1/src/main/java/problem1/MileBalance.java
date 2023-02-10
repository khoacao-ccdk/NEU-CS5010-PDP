package problem1;

/**
 * Represents a FrequentFlyer's mileage balance
 *
 * @author Cody Cao
 */
public class MileBalance {

  private int totalMile;
  private int earnedMile;
  private int expiringMile;

  /**
   * Create a new MileBalance object that holds information about a frequent flyer's mileage
   * balance
   *
   * @param totalMile    an int represents the total number of mileage the frequent flyer currently
   *                     has
   * @param earnedMile   an int represents the number of mileage the frequent flyer has earned this
   *                     year
   * @param expiringMile an int represents the number of mileage expiring by the end of this year
   */
  public MileBalance(int totalMile, int earnedMile, int expiringMile) {
    this.totalMile = totalMile;
    this.earnedMile = earnedMile;
    this.expiringMile = expiringMile;
  }

  /**
   * @return total mileage available in user's account
   */
  public int getTotalMile() {
    return totalMile;
  }

  /**
   * @param totalMile new mileage balance in FrequentFlyer's account
   */
  public void setTotalMile(int totalMile) {
    this.totalMile = totalMile;
  }

  /**
   * @return FrequentFlyer's number of mileage earned this year
   */
  public int getEarnedMile() {
    return earnedMile;
  }

  /**
   * @param earnedMile FrequentFlyer's number of mileage earned this year
   */
  public void setEarnedMile(int earnedMile) {
    this.earnedMile = earnedMile;
  }

  /**
   * @return FrequentFlyer's number of mileage expiring by the end of this year
   */
  public int getExpiringMile() {
    return expiringMile;
  }

  /**
   * @param expiringMile FrequentFlyer's number of mileage expiring by the end of this year
   */
  public void setExpiringMile(int expiringMile) {
    this.expiringMile = expiringMile;
  }
}
