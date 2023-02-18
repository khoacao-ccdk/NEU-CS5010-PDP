import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

  Inventory i;
  Product p1, p2, p3;

  @BeforeEach
  void setUp() {
    p1 = new Shampoo("Clear", "Clear Men", 5.5, 1);
    p2 = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
    p3 = new Cheese("Anchor", "Blue Cheese", 10, 1.5);
    i = Inventory.getInstance();
    i.addNewStock(new StockItem(p1, 10));
    i.addNewStock(new StockItem(p2, 20));

  }

  @Test
  void getInstance() {
    assertSame(i, Inventory.getInstance());
  }

  @Test
  void addNewStock() {
    i.addNewStock(new StockItem(p3, 30));
    assertEquals(1, i.getHouseholdStock().keySet().size());
    assertEquals(2, i.getGroceryStock().keySet().size());
  }

  @Test
  void isEnoughStock() {
    assertTrue(i.isEnoughStock(p1, 5));
    assertFalse(i.isEnoughStock(p2, 30));
  }

  @Test
  void getHouseholdStock() {
    Map<Household, StockItem> test = new HashMap<>();
    test.put((Household) p1, new StockItem(p1, 10));
    assertEquals(test, i.getHouseholdStock());
  }

  @Test
  void getGroceryStock() {
    Map<Grocery, StockItem> test = new HashMap<>();
    test.put((Grocery) p2, new StockItem(p2, 20));
    test.put((Grocery) p3, new StockItem(p3, 30));
    assertEquals(test, i.getGroceryStock());
  }

  @Test
  void makePurchase() {
    i.makePurchase(p1, 5);
    i.makePurchase(p2, 10);
    assertFalse(i.isEnoughStock(p1, 6));
    assertFalse(i.isEnoughStock(p2, 11));
  }

  @Test
  void makePurchaseItemNotInStock() {
    i.makePurchase(null, 10);
    i.makePurchase(new Beer("Tiger", "", 6.5, 21, 3), 3);

    assertTrue(i.isEnoughStock(p1, 10));
    assertTrue(i.isEnoughStock(p2, 20));
  }

  @Test
  void getTotalStockValue() {
    assertEquals(265, i.getTotalStockValue());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Inventory{");
    sb.append("groceryStock=").append(i.getGroceryStock());
    sb.append(", householdStock=").append(i.getHouseholdStock());
    sb.append('}');

    assertEquals(sb.toString(), i.toString());
  }
}