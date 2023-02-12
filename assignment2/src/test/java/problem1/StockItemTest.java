import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockItemTest {

  StockItem s;
  Product p;

  @BeforeEach
  void setUp() {
    p = new Shampoo("Clear", "Clear Men", 5.5, 1);
    s = new StockItem(p, 50);
  }

  @Test
  void getProduct() {
    assertSame(p, s.getProduct());
  }

  @Test
  void setProduct() {
    Product test = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
    s.setProduct(test);
    assertSame(test, s.getProduct());
  }

  @Test
  void getQuantity() {
    assertEquals(50, s.getQuantity());
  }

  @Test
  void setQuantity() {
    s.setQuantity(100);
    assertEquals(100, s.getQuantity());
  }

  @Test
  void isEnoughStock() {
    assertTrue(s.isEnoughStock(50));
    assertFalse(s.isEnoughStock(100));
  }

  @Test
  void makePurchase() {
    s.makePurchase(10);
    assertEquals(40, s.getQuantity());
  }

  @Test
  void testEquals() {
    StockItem test = new StockItem(new Shampoo("Clear", "Clear Men", 5.5, 1), 50);
    assertEquals(s, s);
    assertTrue(s.equals(test));
    assertTrue(s.equals(s));
    assertFalse(s.equals(null));
    assertFalse(s.equals(new String()));
  }

  @Test
  void testHashCode() {
    StockItem test = new StockItem(new Shampoo("Clear", "Clear Men", 5.5, 1), 50);
    assertEquals(test.hashCode(), s.hashCode());
  }

  @Test
  void name() {
    StringBuffer sb = new StringBuffer("StockItem{");
    sb.append("product=").append(s.getProduct());
    sb.append(", quantity=").append(s.getQuantity());
    sb.append('}');

    assertEquals(sb.toString(), s.toString());
  }
}