import java.util.Objects;

/**
 * Represents an item in stock
 *
 * @author Cody Cao
 */
public class StockItem {

  private Product product;
  private int quantity;

  /**
   * Creates a new StockItem object that keeps track of quantity of each product
   *
   * @param product  the product being added to Stock expressed as Product
   * @param quantity number of products in stock expressed as Integer
   */
  public StockItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * @return a Product object represents the product's information
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Set the product being kept track of
   *
   * @param product a Product object represents the product's information
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * @return an int represents the number of product in stock
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Set number of product in stock
   *
   * @param quantity an int represents the number of product in stock
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * @param numItems number of items being checked
   * @return true if the product still has items in stock, false otherwise
   */
  public boolean isEnoughStock(int numItems) {
    return this.quantity >= numItems ? true : false;
  }

  /**
   * Reduces the quantity of the product in the event of a purchase
   *
   * @param numItem an int represents number of item being purchased
   */
  public void makePurchase(int numItem) {
    this.quantity -= numItem;
  }

  /**
   * @param o another StockItem object
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
    StockItem stockItem = (StockItem) o;
    return quantity == stockItem.quantity && product.equals(stockItem.product);
  }

  /**
   * @return an int represents the hashcode of StockItem object
   */
  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }

  /**
   * @return a String with the information about the object
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("StockItem{");
    sb.append("product=").append(product);
    sb.append(", quantity=").append(quantity);
    sb.append('}');
    return sb.toString();
  }
}
