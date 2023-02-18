import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReceiptTest {

  Receipt r;
  ProductList<Product> receivedItems, outOfStockItems, removedItems;

  @BeforeEach
  void setUp() {
    receivedItems = new ProductList<>();
    outOfStockItems = new ProductList<>();
    removedItems = new ProductList<>();

    receivedItems.addProduct(new Shampoo("Clear", "Clear Men", 5.5, 1), 3);
    outOfStockItems.addProduct(new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5), 2);
    r = new Receipt(16.5, receivedItems, outOfStockItems, removedItems);
  }

  @Test
  void getTotalPrice() {
    assertEquals(16.5, r.getTotalPrice());
  }

  @Test
  void setTotalPrice() {
    r.setTotalPrice(150);
    assertEquals(150, r.getTotalPrice());
  }

  @Test
  void getReceivedItems() {
    ProductList<Product> test = new ProductList<>();
    test.addProduct(new Shampoo("Clear", "Clear Men", 5.5, 1),3);
    assertEquals(test, r.getReceivedItems());
  }

  @Test
  void setReceivedItems() {
    r.setReceivedItems(new ProductList<>());
    assertEquals(new ProductList<>(), r.getReceivedItems());
  }

  @Test
  void getOutOfStockItems() {
    ProductList<Product> test = new ProductList<>();
    test.addProduct(new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5),2);
    assertEquals(test, r.getOutOfStockItems());
  }

  @Test
  void setOutOfStockItems() {
    r.setOutOfStockItems(new ProductList<>());
    assertEquals(new ProductList<>(), r.getOutOfStockItems());
  }

  @Test
  void getRemovedItems() {
    assertEquals(new ProductList<>(), r.getRemovedItems());
  }

  @Test
  void setRemovedItems() {
    removedItems.addProduct(new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5),1);
    r.setRemovedItems(removedItems);

    ProductList<Product> test = new ProductList<>();
    test.addProduct(new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5), 1);
    assertEquals(test, r.getRemovedItems());
  }

  @Test
  void testEquals() {
    Receipt equal = new Receipt(16.5, receivedItems, outOfStockItems, removedItems),
        diff = new Receipt(0, null, null, null);
    assertEquals(r, equal);
    assertEquals(r, r);
    assertNotEquals(r, diff);
    assertNotEquals(r, null);
    assertNotEquals(r, new String());
  }

  @Test
  void testHashCode() {
    Receipt equal = new Receipt(16.5, receivedItems, outOfStockItems, removedItems),
        diff = new Receipt(0, null, null, null);

    assertEquals(equal.hashCode(), r.hashCode());
    assertNotEquals(diff.hashCode(), r.hashCode());
  }

  @Test
  void testToString() {
    String test = new StringBuilder("Receipt: ")
        .append("totalPrice=").append(16.5)
        .append("\nList of products Customer received: \n").append(receivedItems.toString())
        .append("\nList of products that are out of stock \n").append(outOfStockItems.toString())
        .append("\nList of products that were removed \n").append(removedItems.toString())
        .toString();

    assertEquals(test.toString(), r.toString());
  }
}