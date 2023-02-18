import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Singleton class represents the supermarket's inventory (Not implementing equals and hashcode due
 * to the nature of Singleton class)
 *
 * @author Cody Cao
 */
public class Inventory {

  private static Inventory instance;
  Map<Grocery, StockItem> groceryStock;
  Map<Household, StockItem> householdStock;

  /**
   * @return the Singleton Inventory object represents the supermarket's inventory
   */
  public static Inventory getInstance() {
    if (instance == null) {
      instance = new Inventory();
    }
    return instance;
  }

  /**
   * Creates a new Inventory object that holds information of every grocery and household products
   * currently in stock
   */
  private Inventory() {
    this.groceryStock = new HashMap<>();
    this.householdStock = new HashMap<>();
  }

  /**
   * Add a new product to the inventory
   *
   * @param item a StockItem object with information of the product and number of items
   */
  public void addNewStock(StockItem item) {
    Product product = item.getProduct();
    if (product instanceof Grocery) {
      groceryStock.put((Grocery) product, item);
    } else {
      householdStock.put((Household) product, item);
    }
  }

  /**
   * Check whether there is enough stock of the item
   *
   * @param product the product of which stock is being checked
   * @param numItem number of the product's item being checked
   * @return true if there is enough number of product's items in stock, false otherwise
   */
  public boolean isEnoughStock(Product product, int numItem) {
    if (product instanceof Grocery && groceryStock.containsKey((Grocery) product)) {
      return groceryStock.get((Grocery) product).isEnoughStock(numItem);
    }

    if (product instanceof Household && householdStock.containsKey((Household) product)) {
      return householdStock.get((Household) product).isEnoughStock(numItem);
    }

    return false;
  }

  /**
   * @return a HashMap object represents the household stock of the supermarket
   */
  public Map<Household, StockItem> getHouseholdStock() {
    return this.householdStock;
  }

  /**
   * @return a HashMap object represents the grocery stock of the supermarket
   */
  public Map<Grocery, StockItem> getGroceryStock() {
    return this.groceryStock;
  }

  /**
   * Adjust the stock in the event of a product being purchased
   *
   * @param productToPurchase a product being purchased, expressed as a Product object
   * @param numItem           number of items being purchased, expressed as an Integer
   */
  public void makePurchase(Product productToPurchase, int numItem) {
    //Check current stock before purchasing
    if (!isEnoughStock(productToPurchase, numItem)) {
      return;
    }

    StockItem stockItem = (productToPurchase instanceof Grocery)
        ? this.groceryStock.get(productToPurchase)
        : this.householdStock.get(productToPurchase);

    if (stockItem != null) {
      stockItem.makePurchase(numItem);
    }
  }

  /**
   * @return a double represents total retail value of all items in stock
   */
  public double getTotalStockValue() {
    double sum = 0;

    for (Entry<Grocery, StockItem> entry : groceryStock.entrySet()) {
      double productPrice = entry.getKey().getPrice();
      sum += productPrice * entry.getValue().getQuantity();
    }

    for (Entry<Household, StockItem> entry : householdStock.entrySet()) {
      double productPrice = entry.getKey().getPrice();
      sum += productPrice * entry.getValue().getQuantity();
    }

    return sum;
  }

  /**
   * @return a String represents the information of inventory
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Inventory{");
    sb.append("groceryStock=").append(groceryStock);
    sb.append(", householdStock=").append(householdStock);
    sb.append('}');
    return sb.toString();
  }
}
