import java.util.Objects;

/**
 * Represents a Household Product object
 *
 * @author Cody Cao
 */
public abstract class Household extends Product {

  private int numUnit;

  /**
   * Creates a new Household Product object. This product does not have any age restriction, and the
   * minimumAge will be set to 0
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param numUnit      an int represents the number of units in an individual package
   */
  protected Household(String manufacturer, String productName, double price, int numUnit) {
    super(manufacturer, productName, price);
    this.numUnit = numUnit;
  }

  /**
   * Creates a new Household Product object. This product has a minimum age restriction.
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param minimumAge   an int represents the minimum age required to purchase this product
   * @param numUnit      an int represents the number of units in an individual package
   */
  protected Household(String manufacturer, String productName, double price, int minimumAge,
      int numUnit) {
    super(manufacturer, productName, price, minimumAge);
    this.numUnit = numUnit;
  }

  /**
   * @return number of individual units per package
   */
  public int getNumUnit() {
    return numUnit;
  }

  /**
   * Set product's number of individual units per package
   *
   * @param numUnit an int represents number of individual units per package
   */
  public void setNumUnit(int numUnit) {
    this.numUnit = numUnit;
  }

  /**
   * @param o another Household object
   * @return true if the two Household objects are equal structurally
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Household household = (Household) o;
    return numUnit == household.numUnit;
  }

  /**
   * @return an int represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), numUnit);
  }

  /**
   * @return a String represents the object's information
   */
  @Override
  public String toString() {
    return "Household{" +
        "numUnit=" + numUnit +
        "} " + super.toString();
  }
}
