import java.util.Objects;

/**
 * Represents a Customer with their information including name, age, and shopping cart
 */
public class Customer {

  private Name name;
  private ShoppingCart shoppingCart;

  private int age;

  /**
   * Constructs a new Customer object
   *
   * @param name         customer's name, expressed as String
   * @param shoppingCart customer's shopping cart, expressed as a ShoppingCart object
   * @param age          customer's age, expresses as an Integer
   */
  public Customer(Name name, int age, ShoppingCart shoppingCart) {
    this.name = name;
    this.shoppingCart = shoppingCart;
    this.age = age;
  }

  /**
   * @return customer's name, expressed as String
   */
  public Name getName() {
    return name;
  }

  /**
   * Set customer's name
   *
   * @param name customer's name, expressed as String
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * @return customer's shopping cart, expressed as a ShoppingCart object
   */
  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Set customer's shopping cart
   *
   * @param shoppingCart customer's shopping cart, expressed as a ShoppingCart object
   */
  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  /**
   * @return customer's age, expresses as an Integer
   */
  public int getAge() {
    return age;
  }

  /**
   * Set customer's age\
   *
   * @param age customer's age, expresses as an Integer
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @param o another Customer object
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
    Customer customer = (Customer) o;
    return age == customer.age && Objects.equals(name, customer.name)
        && Objects.equals(shoppingCart, customer.shoppingCart);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, shoppingCart, age);
  }

  /**
   * @return a String with customer's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Customer{");
    sb.append("name=").append(name);
    sb.append(", shoppingCart=").append(shoppingCart);
    sb.append(", age=").append(age);
    sb.append('}');
    return sb.toString();
  }
}
