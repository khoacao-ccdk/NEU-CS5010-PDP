import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an Order Processor which fulfills the customer's order
 *
 * @author Cody Cao
 */
public class OrderProcessor {

  private ShoppingCart customerCart;
  private Customer customer;
  private ProductList<Product> receivedItems, outOfStockItems, removedItems;

  /**
   * Creates a new OrderProcessor object
   *
   * @param customer customer's account information, along with their shopping cart
   */
  public OrderProcessor(Customer customer) {
    this.customer = customer;
    this.customerCart = customer.getShoppingCart();
    this.receivedItems = new ProductList<>();
    this.outOfStockItems = new ProductList<>();
    this.removedItems = new ProductList<>();
  }

  /**
   * Fulfills customer's order. After the order is fulfilled, the customer's cart will be emptied
   * and a receipt will be returned
   *
   * @return a Receipt object with the total price paid by customer, list of items the customer
   * receipt, list of items that were out of stock and could not be replaced, and list of removed
   * items due to customer's not meeting age requirements
   */
  public Receipt fulfillOrder() {
    Inventory inventory = Inventory.getInstance();
    ProductList<Product> cartItems = this.customerCart.getCartItems();
    List<Product> productsToReplace = new ArrayList<>(cartItems.getProductsInList().size());

    //Remove items from customer's cart if customer does not meet age requirements
    filterItemByCustomerAge();
    for (Product product : cartItems.getProductsInList()) {
      int numItems = cartItems.getProductQuantity(product);

      if (inventory.isEnoughStock(product, numItems)) {
        receivedItems.addProduct(product, numItems);
      } else {
        productsToReplace.add(product);
      }
    }
    for (Product productToReplace : productsToReplace){
      substituteProduct(productToReplace);
      cartItems.removeProduct(productToReplace);
    }

    updateInventory();
    //Empty customer's cart
    customer.setShoppingCart(new ShoppingCart());
    Receipt receipt = createReceipt();
    return receipt;
  }

  /**
   * @param item an item the customer wants to purchase
   * @return true if the customer meets the age requirements to purchase the item, false otherwise
   */
  private boolean customerMeetAgeRequirements(Product item) {
    int itemMinimumAge = item.getMinimumAge();

    return itemMinimumAge == Product.PRODUCT_NO_AGE_RESTRICTION
        || itemMinimumAge <= customer.getAge();
  }

  /**
   * Remove item from customer's cart if customer does not meet item's minimum age requirements
   */
  private void filterItemByCustomerAge() {
    ProductList<Product> cartItems = this.customerCart.getCartItems();
    Set<Product> items = cartItems.getProductsInList();
    Iterator<Product> iter = cartItems.getProductsInList().iterator();
    while (iter.hasNext()) {
      Product item = (Product) iter.next();
      if (!customerMeetAgeRequirements(item)) {
        //Add item to list of removedItems
        removedItems.addProduct(item, cartItems.getProductQuantity(item));
        iter.remove();
      }
    }
  }

  /**
   * Replace the product from the customer's cart for a similar product. If the product cannot be
   * replaced, remove it from the customer's cart
   *
   * @param productToReplace a Product object from the customer's cart that needs to be replaced
   */
  private void substituteProduct(Product productToReplace) {
    Inventory inventory = Inventory.getInstance();
    ProductList<Product> cartItems = this.customerCart.getCartItems();
    int numItemToReplace = cartItems.getProductQuantity(productToReplace);

    //Get list of items in stock to find a replacement product
    Map<? extends Product, StockItem> productStock = productToReplace instanceof Grocery
        ? inventory.getGroceryStock()
        : inventory.getHouseholdStock();

    for (Product product : productStock.keySet()) {
      System.out.println("Watching: "+product.toString());
      //Check whether the customer can purchase the item
      if (!customerMeetAgeRequirements(product)) {
        continue;
      }

      //If there is not enough stock if this product to replace, continue
      if (!inventory.isEnoughStock(product, numItemToReplace)
          || product.getClass() != productToReplace.getClass()) {
        continue;
      }

      //If the weight of this grocery product is less than that of the product to replace, continue
      if ((product instanceof Grocery)
          && ((Grocery) product).getWeight() < ((Grocery) productToReplace).getWeight()) {
        continue;
      }

      /*
       * If number of units per package of this household product is less than that of the product to replace,
       * continue
       */
      if ((product instanceof Household)
          && ((Household) product).getNumUnit() < ((Household) productToReplace).getNumUnit()) {
        continue;
      }
      //Swap the two products after every condition has been checked
      cartItems.addProduct(product, numItemToReplace);
      receivedItems.addProduct(product, numItemToReplace);
      return;
    }
      //If no replacement was made, remove the item from customer's cart
    outOfStockItems.addProduct(productToReplace, cartItems.getProductQuantity(productToReplace));
  }

  /**
   * Update the supermarket's inventory when the customer's order is fulfilled
   */
  private void updateInventory() {
    Inventory inventory = Inventory.getInstance();

    for (Product item : this.receivedItems.getProductsInList()) {
      inventory.makePurchase(item,
          this.receivedItems.getProductQuantity(item)
      );
    }
  }

  /**
   * @return a Receipt object represents the Customer's receipt
   */
  private Receipt createReceipt() {
    return new Receipt(this.customerCart.getCartTotalCost(),
        this.receivedItems,
        this.outOfStockItems,
        this.removedItems);
  }

  /**
   * @param o another OrderProcessor object
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
    OrderProcessor that = (OrderProcessor) o;
    return Objects.equals(customerCart, that.customerCart) && Objects.equals(
        customer, that.customer) && Objects.equals(receivedItems, that.receivedItems)
        && Objects.equals(outOfStockItems, that.outOfStockItems)
        && Objects.equals(removedItems, that.removedItems);
  }

  /**
   * @return the object's hashcode, represented as an Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(customerCart, customer, receivedItems, outOfStockItems, removedItems);
  }

  /**
   * @return a String represents the OrderProcessor object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("OrderProcessor{");
    sb.append('}');
    return sb.toString();
  }
}
