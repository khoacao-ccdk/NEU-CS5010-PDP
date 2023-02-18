import java.util.Objects;

/**
 * Represents a Product object
 *
 * @author Cody Cao
 */
public abstract class Product {

  /**
   * Constant PRODUCT_NO_AGE_RESTRICTION be used when product has no age restriction
   */
  public static  final  int PRODUCT_NO_AGE_RESTRICTION = 0;
  private String manufacturer;
  private String productName;
  private double price;
  private int minimumAge;

  /**
   * Creates a new Product object. This product does not have any age restriction, and the
   * minimumAge will be set to 0
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   */
  protected Product(String manufacturer, String productName, double price) {
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.minimumAge = PRODUCT_NO_AGE_RESTRICTION;
  }

  /**
   * Creates a new Product object. This product has a minimum age restriction.
   *
   * @param manufacturer a String represents the manufacturer's name
   * @param productName  a String represents the product's name
   * @param price        a double represents the product's price
   * @param minimumAge   an int represents the minimum age required to purchase this product
   */
  protected Product(String manufacturer, String productName, double price, int minimumAge) {
    this.manufacturer = manufacturer;
    this.productName = productName;
    this.price = price;
    this.minimumAge = minimumAge;
  }

  /**
   * @return a String represents the product's manufacturer name
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Set the product's manufacturer name
   *
   * @param manufacturer a String represents the product's manufacturer name
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * @return a String represents the product's name
   */
  public String getProductName() {
    return productName;
  }

  /**
   * Set the product's name
   *
   * @param productName a String represents the product's name
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return a double represents the product's price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Set the product's price
   *
   * @param price a double represents the product's price
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @return an int represents the minimum age required in order to purchase this product. If the
   * product does not have an age restriction, return 0
   */
  public int getMinimumAge() {
    return minimumAge;
  }

  /**
   * Set the minimum age required in order to purchase this product. If the product does not have an
   * age restriction, use 0
   *
   * @param minimumAge an int represents the minimum age required in order to purchase this
   *                   product.
   */
  public void setMinimumAge(int minimumAge) {
    this.minimumAge = minimumAge;
  }

  /**
   *
   * @param o another Product object
   * @return true if the two Product objects are structurally equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Double.compare(product.price, price) == 0 && minimumAge == product.minimumAge
        && Objects.equals(manufacturer, product.manufacturer) && Objects.equals(
        productName, product.productName);
  }

  /**
   *
   * @return an int represents the hashCode of the Product object
   */
  @Override
  public int hashCode() {
    return Objects.hash(manufacturer, productName, price, minimumAge);
  }

  /**
   *
   * @return a String with the product's information
   */
  @Override
  public String toString() {
    return "Product{" +
        "manufacturer='" + manufacturer + '\'' +
        ", productName='" + productName + '\'' +
        ", price=" + price +
        ", minimumAge=" + minimumAge +
        '}';
  }
}
