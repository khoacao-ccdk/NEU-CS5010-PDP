import java.util.Objects;

/**
 * Represents a customer's shopping cart
 *
 * @author Cody Cao
 */
public class ShoppingCart {

  /**
   * Default number of item being added to cart
   */
  public static final int DEFAULT_NUM_ITEM = 1;
  private ProductList<Product> cartItems;

  /**
   * Constructs a new ShoppingCart object
   */
  public ShoppingCart() {
    this.cartItems = new ProductList<>();
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
   * @param product  the product the customer wants to add to their cart
   * @param numItems number of cartItems the customer wants to add to their cart
   */
  public void addItemToCart(Product product, int numItems) {
    //Check if there is enough stock of the product available before adding them to cart
    Inventory inventory = Inventory.getInstance();
    if (!inventory.isEnoughStock(product, numItems)) {
      System.out.println("Not enough stock of this item!");
      return;
    }

    //Add the item to the map
    cartItems.addProduct(product, numItems);
  }

  /**
   * Removes or reduces the number of item of a particular product within the shopping cart
   *
   * @param product  product being removed/adjusted expressed as a Product object
   * @param numItems number of items being removed. If number of items being removed is larger than
   *                 the number of item in cart, remove the product from cart
   */
  public void removeItemFromCart(Product product, int numItems) {
    int numProductInCart = cartItems.getProductQuantity(product);
    //Trying to remove a product not in cart
    if (numProductInCart < 1) {
      return;
    }
    cartItems.removeProduct(product);
    //If the number of items in cart larger than the number of items being removed, add the remaining number back to cart
    if (numProductInCart > numItems) {
      cartItems.addProduct(product, numProductInCart - numItems);
    }
  }

  /**
   * @return a ProductList object with products and number of items of each product currently in
   * customer's cart
   */
  public ProductList<Product> getCartItems() {
    return this.cartItems;
  }

  /**
   * @return a double represents the total cost of all cartItems in the shopping cart
   */
  public double getCartTotalCost() {
    double sum = 0;

    //Iterate over the product list and sum up products' price multiplied by their amount in cart
    for (Product product : cartItems.getProductsInList()) {
      double itemPrice = product.getPrice();
      sum += itemPrice * cartItems.getProductQuantity(product);
    }

    return sum;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShoppingCart that = (ShoppingCart) o;
    return Objects.equals(cartItems, that.cartItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartItems);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ShoppingCart{");
    sb.append("cartItems=").append(cartItems);
    sb.append('}');
    return sb.toString();
  }
}
