/**
 * Represents a Beer object under the Grocery abstract class
 *
 * @author Cody Cao
 */
public class Beer extends Grocery {

  /**
   * Creates a new Beer object. This product does not have any age restriction, and the minimumAge
   * will be set to 0
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param weight       a double represents the weight of the product
   */
  public Beer(String manufacturer, String productName, double price, double weight) {
    super(manufacturer, productName, price, weight);
  }

  /**
   * Creates a new Beer object. This product has a minimum age restriction
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param minimumAge   an int represents the minimum age required to purchase this product
   * @param price        a double represents the product's price
   * @param weight       a double represents the weight of the product
   */
  public Beer(String manufacturer, String productName, double price, int minimumAge,
      double weight) {
    super(manufacturer, productName, price, minimumAge, weight);
  }


}
