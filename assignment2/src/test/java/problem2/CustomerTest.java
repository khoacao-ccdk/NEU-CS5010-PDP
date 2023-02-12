import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  Customer c;
  @BeforeEach
  void setUp() {
    c = new Customer(new Name("Cody", "Cao"), 21, new ShoppingCart());
  }

  @Test
  void getName() {
    assertEquals(new Name("Cody", "Cao"), c.getName());
  }

  @Test
  void setName() {
    c.setName(new Name("Khoa", null));
    assertEquals(new Name("Khoa", null), c.getName());
  }

  @Test
  void getAge() {
    assertEquals(21, c.getAge());
  }

  @Test
  void setAge() {
    c.setAge(20);
    assertEquals(20, c.getAge());
  }

  @Test
  void getCart() {
    assertEquals(new ShoppingCart(), c.getShoppingCart());
  }

  @Test
  void setCart() {
    c.setShoppingCart(null);
    assertNull(c.getShoppingCart());
  }

  @Test
  void testEquals() {
    Customer equal = new Customer(new Name("Cody", "Cao"), 21, new ShoppingCart());
    Customer diff = new Customer(null, 1, null);
    assertEquals(c, equal);
    assertEquals(c, c);
    assertNotEquals(c, diff);
    assertNotEquals(c, null);
    assertNotEquals(c, new String());
  }

  @Test
  void testHashcode() {
    Customer equal = new Customer(new Name("Cody", "Cao"), 21, new ShoppingCart());
    Customer diff = new Customer(null, 1, null);
    assertNotEquals(diff.hashCode(), c.hashCode());
    assertEquals(equal.toString(), c.toString());
  }

  @Test
  void testToString(){
    StringBuffer sb = new StringBuffer("Customer{");
    sb.append("name=").append(new Name("Cody", "Cao"));
    sb.append(", shoppingCart=").append(new ShoppingCart());
    sb.append(", age=").append(21);
    sb.append('}');

    assertEquals(sb.toString(), c.toString());
  }
}