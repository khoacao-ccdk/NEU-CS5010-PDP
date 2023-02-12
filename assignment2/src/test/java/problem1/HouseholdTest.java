import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HouseholdTest {

  private Household p;

  @BeforeEach
  void setUp() {
    p = new Shampoo("Clear", "Clear Men", 5.5, 1);
  }

  @Test
  void getManufacturer() {
    assertSame("Clear", p.getManufacturer());
  }

  @Test
  void setManufacturer() {
    p.setManufacturer("Olay");
    assertSame("Olay", p.getManufacturer());
  }

  @Test
  void getProductName() {
    assertSame("Clear Men", p.getProductName());
  }

  @Test
  void setProductName() {
    p.setProductName("Clear Women");
    assertSame("Clear Women", p.getProductName());
  }

  @Test
  void getPrice() {
    assertEquals(5.5, p.getPrice());
  }

  @Test
  void setPrice() {
    p.setPrice(6);
    assertEquals(6, p.getPrice());
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
  void testEquals() {
    Household test = new Shampoo("Clear", "Clear Men", 5.5, 1);
    assertEquals(p, p);
    assertTrue(p.equals(test));
    assertFalse(p.equals(null));
    assertFalse(p.equals(new String()));
  }

  @Test
  void testHashCode() {
    Household test = new Shampoo("Clear", "Clear Men", 5.5, 1);
    assertEquals(test.hashCode(), p.hashCode());
  }

  @Test
  void testToString() {
    String test = "Household{" +
        "numUnit=" + 1 +
        "} " +
        "Product{" +
        "manufacturer='" + "Clear" + '\'' +
        ", productName='" + "Clear Men" + '\'' +
        ", price=" + "5.5" +
        ", minimumAge=" + Product.PRODUCT_NO_AGE_RESTRICTION +
        '}';

    assertTrue(test.equals(p.toString()));
  }

  @Test
  void getNumUnit() {
    assertEquals(1, p.getNumUnit());
  }

  @Test
  void setNumUnit() {
    p.setNumUnit(2);
    assertEquals(2, p.getNumUnit());
  }
}