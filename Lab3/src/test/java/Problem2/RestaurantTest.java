package Problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantTest {

  Menu m;
  Address a;
  Restaurant r;

  List<String> meals, deserts, beverages, drinks;

  @BeforeEach
  void setUp() {
    meals = List.of("breakfast", "lunch", "dinner");
    deserts = List.of("pancake", "banana");
    beverages = List.of("wine", "beer");
    drinks = List.of("water");
    m = new Menu(meals, deserts, beverages, drinks);
    a = new Address("401 Terry Ave N",
        "Seattle",
        "98109",
        "WA",
        "USA");
    r = new Restaurant(a, m, true);
  }

  @Test
  void getAddress() {
    assertEquals(new Address("401 Terry Ave N",
        "Seattle",
        "98109",
        "WA",
        "USA"), r.getAddress());
  }

  @Test
  void setAddress() {
    Address test = new Address("225 Terry Ave N",
        "Tacoma",
        "98121",
        "ON",
        "Canada");
    r.setAddress(test);
    assertEquals(new Address("225 Terry Ave N",
        "Tacoma",
        "98121",
        "ON",
        "Canada"), r.getAddress());
  }

  @Test
  void getMenu() {
    assertEquals(new Menu(meals, deserts, beverages, drinks), r.getMenu());
  }

  @Test
  void setMenu() {
    Menu test = new Menu(null, null, null, null);
    r.setMenu(test);
    assertEquals(new Menu(null, null, null, null), r.getMenu());
  }

  @Test
  void isOpen() {
    assertEquals(true, r.isOpen());
  }

  @Test
  void setOpen() {
    r.setOpen(false);
    assertEquals(false, r.isOpen());
  }

  @Test
  void testEquals() {
    Restaurant equal = new Restaurant(a, m, true);
    Restaurant diff = new Restaurant(null, null, false);

    assertEquals(equal, r);
    assertEquals(r, r);
    assertNotEquals(diff, r);
    assertNotEquals(null, r);
    assertNotEquals(new String(), r);
  }

  @Test
  void testHashCode() {
    Restaurant equal = new Restaurant(a, m, true);
    Restaurant diff = new Restaurant(null, null, false);

    assertEquals(r.hashCode(), r.hashCode());
    assertEquals(equal.hashCode(), r.hashCode());
    assertNotEquals(diff.hashCode(), r.hashCode());
    assertNotEquals(r, null);
  }

  @Test
  void testToString() {
    final StringBuffer sb = new StringBuffer("Restaurant{");
    sb.append("address=").append(a);
    sb.append(", menu=").append(m);
    sb.append(", isOpen=").append(true);
    sb.append('}');

    assertEquals(sb.toString(), r.toString());
  }
}