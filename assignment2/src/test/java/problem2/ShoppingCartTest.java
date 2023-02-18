import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {
  Inventory i;
  ShoppingCart cart;
  Product p1, p2, p3;

  @BeforeEach
  void setUp() {
    cart = new ShoppingCart();
    p1 = new Shampoo("Clear", "Clear Men", 5.5, 1);
    p2 = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
    p3 = new Cheese("Anchor", "Blue Cheese", 10, 1.5);
    i = Inventory.getInstance();
    i.addNewStock(new StockItem(p1, 10));
    i.addNewStock(new StockItem(p2, 20));
    i.addNewStock(new StockItem(p3, 10));

    cart.addItemToCart(p1, 5);
    cart.addItemToCart(p2, 5);
  }

  @Test
  void addItemToCart() {
    cart.addItemToCart(p3, 2);
    assertEquals(100, cart.getCartTotalCost());

    //Adding a number of item being more than that in stock
    cart.addItemToCart(p1, 20);
    assertEquals(100, cart.getCartTotalCost());

    //Adding another unit of the same item
    cart.addItemToCart(p1, 1);
    assertEquals(105.5, cart.getCartTotalCost());
    assertEquals(3, cart.getCartItems().getProductsInList().size());
  }

  @Test
  void removeItemFromCart() {
    //Removing an item not in cart
    cart.removeItemFromCart(p3, 1);
    assertEquals(2, cart.getCartItems().getProductsInList().size());

    //Removing a smaller number of items than that currently in cart
    cart.removeItemFromCart(p1, 2);
    assertEquals(2, cart.getCartItems().getProductsInList().size());

    cart.removeItemFromCart(p1, 10);
    assertEquals(1, cart.getCartItems().getProductsInList().size());
  }

  @Test
  void getCartItems() {
    ProductList<Product> test = new ProductList<>();
    test.addProduct(p1, 5);
    test.addProduct(p2, 5);

    assertEquals(test, cart.getCartItems());
  }

  @Test
  void getCartTotalCost() {
    assertEquals(80, cart.getCartTotalCost());
  }

  @Test
  void testEquals() {
    ShoppingCart equal = new ShoppingCart(),
        notEqual = new ShoppingCart();
    equal.addItemToCart(p1, 5);
    equal.addItemToCart(p2, 5);

    assertEquals(cart, equal);
    assertEquals(cart, cart);
    assertNotEquals(cart, notEqual);
    assertNotEquals(cart, null);
    assertNotEquals(cart, new String());
  }

  @Test
  void testHashCode() {
    ShoppingCart equal = new ShoppingCart(),
        notEqual = new ShoppingCart();
    equal.addItemToCart(p1, 5);
    equal.addItemToCart(p2, 5);

    assertNotEquals(notEqual.hashCode(), cart.hashCode());
    assertEquals(equal.hashCode(), cart.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("ShoppingCart{");
    sb.append("cartItems=").append(cart.getCartItems());
    sb.append('}');

    assertEquals(sb.toString(), cart.toString());
  }
}