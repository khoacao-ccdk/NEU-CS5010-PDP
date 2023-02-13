import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a customer's shopping cart
 *
 * @author Cody Cao
 */
public class ShoppingCart {

  //Default number of item being added to cart
  public static final int DEFAULT_NUM_ITEM = 1;
  private Map<Product, Integer> cartItems;

  /**
   * Constructs a new ShoppingCart object
   */
  public ShoppingCart() {
    this.cartItems = new HashMap<>();
  }

  /**
   * Add 1 unit of the product to cart
   *
   * @param product the product the customer wants to add to their cart
   */
  public void addItemToCart(Product product) {
    this.addItemToCart(product, DEFAULT_NUM_ITEM);
  }

  /**
   * Add multiple units of the product to cart
   *
   * @param product the product the customer wants to add to their cart
   * @param numItem number of cartItems the customer wants to add to their cart
   */
  public void addItemToCart(Product product, int numItem) {
    //Check if there is enough stock of the product available before adding them to cart
    Inventory inventory = Inventory.getInstance();
    if (!inventory.isEnoughStock(product, numItem)) {
      System.out.println("Not enough stock of this item!");
      return;
    }

    //Add the item to the map
    cartItems.put(product,
        cartItems.getOrDefault(product, 0) + numItem
    );
  }

  /**
   * @return a HashMap object with products and number of items of each product currently in
   * customer's cart
   */
  public Map<Product, Integer> getCartItems() {
    return this.cartItems;
  }

  /**
   * @return a double represents the total cost of all cartItems in the shopping cart
   */
  public double getCartTotalCost() {
    double sum = 0;

    //Iterate over the hashmap and sum up products' price multiplied by their amount in cart
    for (Entry<Product, Integer> entry : cartItems.entrySet()) {
      double itemPrice = entry.getKey().getPrice();
      sum += itemPrice * entry.getValue();
    }

    return sum;
  }

}
