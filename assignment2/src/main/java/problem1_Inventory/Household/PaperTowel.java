/**
 * Represents a Paper Towel object under the Household abstract class
 *
 * @author Cody Cao
 */
public class PaperTowel extends Household {

  /**
   * Creates a new PaperTowel object. This product does not have any age restriction, and the
   * minimumAge will be set to 0
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param numUnit      an int represents the number of units in an individual package
   */
  protected PaperTowel(String manufacturer, String productName, double price, int numUnit) {
    super(manufacturer, productName, price, numUnit);
  }

  /**
   * Creates a new PaperTowel Product object. This product has a minimum age restriction.
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param minimumAge   an int represents the minimum age required to purchase this product
   * @param numUnit      an int represents the number of units in an individual package
   */
  protected PaperTowel(String manufacturer, String productName, double price, int minimumAge,
      int numUnit) {
    super(manufacturer, productName, price, minimumAge, numUnit);
  }
}
