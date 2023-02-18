import java.util.Objects;

/**
 * Represents a Receive with information about the total price paid, list of received items, removed
 * items, and out of stock items
 *
 * @author Cody Cao
 */
public class Receipt {

  private double totalPrice;
  private ProductList<Product> receivedItems,
      outOfStockItems,
      removedItems;

  /**
   * Creates a new Receipt object
   *
   * @param totalPrice      an int represents the total price the customer paid for their carts
   * @param receivedItems   a List object represents the list of products the customer received
   * @param outOfStockItems a List object represents the list of products that were out of stock
   * @param removedItems    a List object represents the list of products that were removed from the
   *                        order due to customer's age not meeting minimum age requirements
   */
  public Receipt(double totalPrice, ProductList<Product> receivedItems,
      ProductList<Product> outOfStockItems,
      ProductList<Product> removedItems) {
    this.totalPrice = totalPrice;
    this.receivedItems = receivedItems;
    this.outOfStockItems = outOfStockItems;
    this.removedItems = removedItems;
  }

  /**
   * @return the total price that the customer paid
   */
  public double getTotalPrice() {
    return totalPrice;
  }

  /**
   * Set the total price the customer paid
   *
   * @param totalPrice a double represents the total price the customer paid
   */
  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * @return a ProductList object of items that the customer received
   */
  public ProductList<Product> getReceivedItems() {
    return receivedItems;
  }

  /**
   * Set the list of items that the customer received
   *
   * @param receivedItems a ProductList object of items that the customer received
   */
  public void setReceivedItems(ProductList<Product> receivedItems) {
    this.receivedItems = receivedItems;
  }

  /**
   * @return a ProductList object of items that were out of stock and could not be substituted
   */
  public ProductList<Product> getOutOfStockItems() {
    return outOfStockItems;
  }

  /**
   * Set the list of items that were out of stock and could not be substituted
   *
   * @param outOfStockItems a ProductList object of items that were out of stock and could not be
   *                        substituted
   */
  public void setOutOfStockItems(ProductList<Product> outOfStockItems) {
    this.outOfStockItems = outOfStockItems;
  }

  /**
   * @return a ProductList items that were removed due to customer's not meeting the minimum age
   * requirements
   */
  public ProductList<Product> getRemovedItems() {
    return removedItems;
  }

  /**
   * Set the list of tems that were removed due to customer's not meeting the minimum age
   * requirements
   *
   * @param removedItems a ProductList items that were removed due to customer's not meeting the
   *                     minimum age requirements
   */
  public void setRemovedItems(ProductList<Product> removedItems) {
    this.removedItems = removedItems;
  }

  /**
   * @param o another Receipt object
   * @return true if the two receipts are structurally equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receipt receipt = (Receipt) o;
    return Double.compare(receipt.totalPrice, totalPrice) == 0 && receivedItems.equals(
        receipt.receivedItems) && outOfStockItems.equals(receipt.outOfStockItems)
        && removedItems.equals(receipt.removedItems);
  }

  /**
   * @return an int represents the hashcode of the Receipt object
   */
  @Override
  public int hashCode() {
    return Objects.hash(totalPrice, receivedItems, outOfStockItems, removedItems);
  }

  /**
   * @return a String with every information of the receipt
   */
  @Override
  public String toString() {
    return new StringBuilder("Receipt: ")
        .append("totalPrice=").append(totalPrice)
        .append("\nList of products Customer received: \n").append(receivedItems.toString())
        .append("\nList of products that are out of stock \n").append(outOfStockItems.toString())
        .append("\nList of products that were removed \n").append(removedItems.toString())
        .toString();
  }
}
