import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroceryTest {
  private Grocery p;
  @BeforeEach
  void setUp() {
    p = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
  }

  @Test
  void getManufacturer() {
    assertSame("Atlantic", p.getManufacturer());
  }

  @Test
  void setManufacturer() {
    p.setManufacturer("Japan");
    assertSame("Japan", p.getManufacturer());
  }

  @Test
  void getProductName() {
    assertSame("Frozen Salmon", p.getProductName());
  }

  @Test
  void setProductName() {
    p.setProductName("Fresh Salmon");
    assertSame("Fresh Salmon", p.getProductName());
  }

  @Test
  void getPrice() {
    assertEquals(10.5, p.getPrice());
  }

  @Test
  void setPrice() {
    p.setPrice(12);
    assertEquals(12, p.getPrice());
  }

  @Test
  void getMinimumAge() {
    assertEquals(Product.PRODUCT_NO_AGE_RESTRICTION, p.getMinimumAge());
  }

  @Test
  void setMinimumAge() {
    p.setMinimumAge(21);
    assertEquals(21, p.getMinimumAge());
  }

  @Test
  void getWeight() {
    assertEquals(1.5, p.getWeight());
  }

  @Test
  void setWeight() {
    p.setWeight(2.5);
    assertEquals(2.5, p.getWeight());
  }

  @Test
  void testEquals() {
    Grocery test = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
    assertEquals(p, p);
    assertTrue(p.equals(test));
    assertFalse(p.equals(null));
    assertFalse(p.equals(new String()));
  }

  @Test
  void testHashCode() {
    Grocery test = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
    assertEquals(test.hashCode(), p.hashCode());
  }

  @Test
  void testToString() {
    String test =
        "Grocery{" +
            "weight=" + 1.5 +
            "} " +
            "Product{" +
            "manufacturer='" + "Atlantic" + '\'' +
            ", productName='" + "Frozen Salmon" + '\'' +
            ", price=" + 10.5 +
            ", minimumAge=" + Product.PRODUCT_NO_AGE_RESTRICTION +
            '}';
    assertTrue(test.equals(p.toString()));
  }
}