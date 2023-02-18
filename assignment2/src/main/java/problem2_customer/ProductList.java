import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

/**
 * Class ProductList acts as a list to hold information of products and number of items for each
 * product
 *
 * @param <E> A class extends Product class
 * @author Cody Cao
 */
public class ProductList<E extends Product> {

  private Map<E, Integer> productList;

  /**
   * Creates a new ProductList object that keeps a list of Products
   */
  public ProductList() {
    this.productList = new HashMap<>();
  }

  /**
   * @param product  a Product object that need to be added to the list
   * @param numItems number of items of the product being added to the list
   */
  public void addProduct(E product, int numItems) {
    if (!(product instanceof E)) {
      return;
    }
    this.productList.put(product,
        productList.getOrDefault(product, 0)
            + numItems
    );
  }

  /**
   * Removes the specified product from the list
   *
   * @param product product to be removed
   */
  public void removeProduct(E product) {
    this.productList.remove(product);
  }

  /**
   * @return a set represents the list of items currently in product list
   */
  public Set<E> getProductsInList() {
    return this.productList.keySet();
  }

  /**
   * @param product a Product object
   * @return the product's quantity currently in the list. Return 0 if product is not in the list
   */
  public int getProductQuantity(E product) {
    return productList.getOrDefault(product, 0);
  }

  /**
   * @param o another ProductList object
   * @return true if two ProductList objects are structurally equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductList<?> that = (ProductList<?>) o;
    return productList.equals(that.productList);
  }

  /**
   * @return an int represents the hashcode of the ProductList object
   */
  @Override
  public int hashCode() {
    return Objects.hash(productList);
  }

  /**
   * @return a String with information of products in the list
   */
  @Override
  public String toString() {
    int counter = 0;
    StringBuilder sb = new StringBuilder("Items in this list:\n");
    for (Entry<E, Integer> entry : productList.entrySet()) {
      Product item = entry.getKey();
      int numItems = entry.getValue();
      sb.append(counter++)
          .append(". ")
          .append(item.toString())
          .append(", quantity = ")
          .append(numItems)
          .append("\n");
    }
    return sb.toString();
  }
}
