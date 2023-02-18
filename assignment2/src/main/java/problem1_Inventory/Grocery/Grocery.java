import java.util.Objects;

/**
 * Represents a Grocery Product object
 *
 * @author Cody Cao
 */
public abstract class Grocery extends Product {

  private double weight;

  /**
   * Creates a new Grocery Product object. This product does not have any age restriction, and the
   * minimumAge will be set to 0
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param weight       a double represents the weight of the product
   */
  protected Grocery(String manufacturer, String productName, double price, double weight) {
    super(manufacturer, productName, price);
    this.weight = weight;
  }

  /**
   * Creates a new Grocery Product object. This product has a minimum age restriction
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param minimumAge   an int represents the minimum age required to purchase this product
   * @param price        a double represents the product's price
   * @param weight       a double represents the weight of the product
   */
  protected Grocery(String manufacturer, String productName, double price, int minimumAge,
      double weight) {
    super(manufacturer, productName, price, minimumAge);
    this.weight = weight;
  }

  /**
   * @return a double represents the weight per unit of product
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Set the weight per unit of product
   *
   * @param weight a double represents the weight per unit of product
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }

  /**
   * @param o another Grocery object
   * @return true if the two objects are equal structurally
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
    Grocery grocery = (Grocery) o;
    return Double.compare(grocery.weight, weight) == 0;
  }

  /**
   * @return an int represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), weight);
  }

  /**
   * @return a String represents the object's information
   */
  @Override
  public String toString() {
    return "Grocery{" +
        "weight=" + weight +
        "} " + super.toString();
  }
}
