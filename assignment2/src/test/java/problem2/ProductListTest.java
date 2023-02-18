import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductListTest {

  ProductList<Product> pList;
  Product p1, p2;

  @BeforeEach
  void setUp() {
    pList = new ProductList<>();
    p1 = new Shampoo("Clear", "Clear Men", 5.5, 1);
    p2 = new Salmon("Atlantic", "Frozen Salmon", 10.5, 1.5);
    pList.addProduct(p1, 1);
    pList.addProduct(p2, 1);
  }

  @Test
  void addProduct() {
    pList.addProduct(new Cheese("Anchor", "Blue Cheese", 10, 1.5), 2);
    assertEquals(3, pList.getProductsInList().size());

    //Increase number of product
    pList.addProduct(p1, 3);
    assertEquals(4, pList.getProductQuantity(p1));
  }

  @Test
  void removeProduct() {
    pList.removeProduct(p1);
    assertEquals(1, pList.getProductsInList().size());

    //Remove a product not in the list
    pList.removeProduct(new Cheese("Anchor", "Blue Cheese", 10, 1.5));
    assertEquals(1, pList.getProductsInList().size());
  }

  @Test
  void getProductsInList() {
    Set<Product> test = new HashSet<>();
    test.add(p1);
    test.add(p2);
    assertEquals(test, pList.getProductsInList());
  }

  @Test
  void getProductQuantity() {
    assertEquals(1, pList.getProductQuantity(p1));
    assertEquals(0, pList.getProductQuantity(null));
  }

  @Test
  void testEquals() {
    ProductList<Product> equal = new ProductList<>(),
        diff = new ProductList<>();
    equal.addProduct(p1, 1);
    equal.addProduct(p2, 1);

    assertEquals(pList, pList);
    assertEquals(pList, equal);
    assertNotEquals(pList, diff);
    assertNotEquals(pList, null);
    assertNotEquals(pList, new String());
  }

  @Test
  void testHashCode() {
    ProductList<Product> equal = new ProductList<>(),
        diff = new ProductList<>();
    equal.addProduct(p1, 1);
    equal.addProduct(p2, 1);

    assertNotEquals(diff.hashCode(), pList.hashCode());
    assertEquals(equal.hashCode(), pList.hashCode());
  }

  @Test
  void testToString() {
    String test = "Items in this list:\n" +
        "0. Household{numUnit=1} Product{manufacturer='Clear', productName='Clear Men', price=5.5, minimumAge=0}, quantity = 1\n"
        +
        "1. Grocery{weight=1.5} Product{manufacturer='Atlantic', productName='Frozen Salmon', price=10.5, minimumAge=0}, quantity = 1\n";
    assertEquals(test, pList.toString());
  }
}