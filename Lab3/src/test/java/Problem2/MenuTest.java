package Problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {
  List<String> meals,
      deserts,
      beverages,
      drinks;
  Menu m;
  @BeforeEach
  void setUp() {
    meals = List.of("breakfast", "lunch", "dinner");
    deserts = List.of("pancake", "banana");
    beverages = List.of("wine", "beer");
    drinks = List.of("water");
    m = new Menu(meals, deserts, beverages, drinks);
  }

  @Test
  void getMeals() {
    assertTrue(List.of("breakfast", "lunch", "dinner").equals(m.getMeals()));
  }

  @Test
  void setMeals() {
    List<String> test = List.of("breakfast", "lunch");
    m.setMeals(test);
    assertTrue(List.of("breakfast", "lunch").equals(m.getMeals()));
  }

  @Test
  void getDeserts() {
    assertTrue(List.of("pancake", "banana").equals(m.getDeserts()));
  }

  @Test
  void setDeserts() {
    List<String> test = List.of("pancake");
    m.setDeserts(test);
    assertTrue(List.of("pancake").equals(m.getDeserts()));
  }

  @Test
  void getBeverages() {
    assertTrue(List.of("wine", "beer").equals(m.getBeverages()));
  }

  @Test
  void setBeverages() {
    List<String> test = List.of("wine");
    m.setBeverages(test);
    assertTrue(List.of("wine").equals(m.getBeverages()));
  }

  @Test
  void getDrinks() {
    assertTrue(List.of("water").equals(m.getDrinks()));
  }

  @Test
  void setDrinks() {
    List<String> test = new ArrayList<>();
    m.setDrinks(test);
    assertTrue(new ArrayList<>().equals(m.getDrinks()));
  }

  @Test
  void testEquals() {
    Menu identical = new Menu(meals, deserts, beverages, drinks);
    Menu diff = new Menu(null, null, null, null);
    assertTrue(identical.equals(m));
    assertTrue(m.equals(m));
    assertFalse(diff.equals(m));
    assertFalse(m.equals(diff));
    assertNotEquals(null, m);
    assertNotEquals(m, null);
  }

  @Test
  void testHashCode() {
    Menu identical = new Menu(meals, deserts, beverages, drinks);
    Menu diff = new Menu(null, null, null, null);
    assertEquals(identical.hashCode(), m.hashCode());
    assertNotEquals(diff.hashCode(), m.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Menu{");
    sb.append("meals=").append(meals);
    sb.append(", deserts=").append(deserts);
    sb.append(", beverages=").append(beverages);
    sb.append(", drinks=").append(drinks);
    sb.append('}');

    assertEquals(sb.toString(), m.toString());
  }
}