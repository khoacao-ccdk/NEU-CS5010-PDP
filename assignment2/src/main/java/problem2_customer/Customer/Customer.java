/**
 * Represents a Customer account
 *
 * @author Cody Cao
 */
public class Customer {

  private Name name;
  private int age;
  private ShoppingCart cart;

  /**
   * Generates a new Customer object
   *
   * @param name a Name object represents the customer's name
   * @param age  an int represents the customer's age
   * @param cart a ShoppingCart object represents the customer's shopping cart
   */
  public Customer(Name name, int age, ShoppingCart cart) {
    this.name = name;
    this.age = age;
    this.cart = cart;
  }

  /**
   * @return a Name object represents the customer's name
   */
  public Name getName() {
    return name;
  }

  /**
   * Set the customer's name
   *
   * @param name a Name object represents the customer's name
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * @return an int represents the customer's age
   */
  public int getAge() {
    return age;
  }

  /**
   * Set the customer's age
   *
   * @param age an int represents the customer's age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return a ShoppingCart object represents the customer's shopping cart
   */
  public ShoppingCart getCart() {
    return cart;
  }

  /**
   * Set the customer's shopping cart
   *
   * @param cart a ShoppingCart object represents the customer's shopping cart
   */
  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }
}
