package Problem2;

import java.util.List;
import java.util.Objects;

/**
 * Class Menu contains information about a restaurant's menu, including list of meals, desserts,
 * beverages, and drinks
 */
public class Menu {

  private List<String> meals,
      deserts,
      beverages,
      drinks;

  /**
   * Construct a new Menu object
   *
   * @param meals     restaurant's meals, expressed as a List
   * @param deserts   restaurant's deserts, expressed as a List
   * @param beverages restaurant's beverages, expressed as a List
   */
  public Menu(List<String> meals, List<String> deserts, List<String> beverages,
      List<String> drinks) {
    this.meals = meals;
    this.deserts = deserts;
    this.beverages = beverages;
    this.drinks = drinks;
  }

  /**
   * @return restaurant's meals, expressed as a List
   */
  public List<String> getMeals() {
    return meals;
  }

  /**
   * Set restaurant's meals
   *
   * @param meals restaurant's meals, expressed as a List
   */
  public void setMeals(List<String> meals) {
    this.meals = meals;
  }

  /**
   * @return restaurant's deserts, expressed as a List
   */
  public List<String> getDeserts() {
    return deserts;
  }

  /**
   * Set restaurant's desserts
   *
   * @param deserts restaurant's deserts, expressed as a List
   */
  public void setDeserts(List<String> deserts) {
    this.deserts = deserts;
  }

  /**
   * @return restaurant's beverages, expressed as a List
   */
  public List<String> getBeverages() {
    return beverages;
  }

  /**
   * Set restaurant's beverages
   *
   * @param beverages restaurant's beverages, expressed as a List
   */
  public void setBeverages(List<String> beverages) {
    this.beverages = beverages;
  }

  /**
   * @return restaurant's drinks, expressed as a List
   */
  public List<String> getDrinks() {
    return drinks;
  }

  /**
   * Set restaurant's drinks
   *
   * @param drinks restaurant's drinks, expressed as a List
   */
  public void setDrinks(List<String> drinks) {
    this.drinks = drinks;
  }

  /**
   * @param o another Menu object
   * @return true if the two objects are equal structurally
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Menu menu = (Menu) o;
    return Objects.equals(meals, menu.meals) && Objects.equals(deserts,
        menu.deserts) && Objects.equals(beverages, menu.beverages) && Objects.equals(
        drinks, menu.drinks);
  }

  /**
   * @return an Integer represents the Menu's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(meals, deserts, beverages, drinks);
  }

  /**
   * @return a String represents the restaurant's menu information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Menu{");
    sb.append("meals=").append(meals);
    sb.append(", deserts=").append(deserts);
    sb.append(", beverages=").append(beverages);
    sb.append(", drinks=").append(drinks);
    sb.append('}');
    return sb.toString();
  }
}
