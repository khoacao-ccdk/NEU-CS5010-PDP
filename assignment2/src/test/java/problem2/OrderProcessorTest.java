import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderProcessorTest {

  OrderProcessor processor;
  Inventory i;
  Product p1, p2, p3, p4, p5, p6;
  Customer c;

  @BeforeEach
  void setUp() {
    p1 = new Shampoo("Clear", "Clear Men", 5.5, 1);
    p2 = new Salmon("Atlantic", "Frozen Salmon", 10.5, 2);
    p3 = new Cheese("Anchor", "Blue Cheese", 10, 1.5);
    p4 = new Beer("Tiger", "", 10, 21, 3);
    p5 = new PaperTowel("Bounty", "Kitchen towel", 20, 12);
    p6 = new Cheese("Anchor", "Cheddar Cheese", 10, 2);

    i = Inventory.getInstance();
    i.addNewStock(new StockItem(p1, 10));
    i.addNewStock(new StockItem(p2, 20));
    i.addNewStock(new StockItem(p3, 3));
    i.addNewStock(new StockItem(p4, 10));
    i.addNewStock(new StockItem(p5, 10));
    i.addNewStock(new StockItem(p6, 10));

    c = new Customer(new Name("Cody", "Cao"), 20, new ShoppingCart());
    c.getShoppingCart().addItemToCart(p1, 2);
    c.getShoppingCart().addItemToCart(p2, 3);
    c.getShoppingCart().addItemToCart(p3, 2);
    c.getShoppingCart().addItemToCart(p4, 1);
    c.getShoppingCart().addItemToCart(p5, 3);

    processor = new OrderProcessor(c);
  }

  @Test
  void fulfillOrder() {
    //Given p5 and p3 were out of stock at the time the order was processed
    i.getHouseholdStock().remove(p5);
    i.getGroceryStock().remove(p3);

    ProductList<Product> receivedItems = new ProductList<>(),
        outOfStockItems = new ProductList<>(),
        removedItems = new ProductList<>();
    receivedItems.addProduct(p1, 2);
    receivedItems.addProduct(p2, 3);
    receivedItems.addProduct(p6, 2);
    removedItems.addProduct(p4, 1);
    outOfStockItems.addProduct(p5, 3);

    Receipt expected = new Receipt(62.5, receivedItems, outOfStockItems, removedItems);

    assertEquals(expected, processor.fulfillOrder());
  }

  @Test
  void testEquals(){
    Customer d =  new Customer(new Name("Cody", "Cao"), 20, new ShoppingCart());
    d.getShoppingCart().addItemToCart(p1, 2);
    d.getShoppingCart().addItemToCart(p2, 3);
    d.getShoppingCart().addItemToCart(p3, 2);
    d.getShoppingCart().addItemToCart(p4, 1);
    d.getShoppingCart().addItemToCart(p5, 3);

    OrderProcessor equal = new OrderProcessor(d), diff = new OrderProcessor(new Customer(null, 10, null));

    assertEquals(processor, processor);
    assertEquals(processor, equal);
    assertNotEquals(processor, diff);
    assertNotEquals(processor, null);
    assertNotEquals(processor, new String());
  }

  @Test
  void testHashcode(){
    Customer d =  new Customer(new Name("Cody", "Cao"), 20, new ShoppingCart());
    d.getShoppingCart().addItemToCart(p1, 2);
    d.getShoppingCart().addItemToCart(p2, 3);
    d.getShoppingCart().addItemToCart(p3, 2);
    d.getShoppingCart().addItemToCart(p4, 1);
    d.getShoppingCart().addItemToCart(p5, 3);

    OrderProcessor equal = new OrderProcessor(d), diff = new OrderProcessor(new Customer(null, 10, null));

    assertEquals(equal.hashCode(), processor.hashCode());
    assertNotEquals(diff.hashCode(), processor.hashCode());
  }

  @Test
  void testToString(){
    assertEquals(new StringBuffer("OrderProcessor{").append("}").toString(), processor.toString());
  }
}