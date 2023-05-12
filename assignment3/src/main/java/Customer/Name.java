package Customer;

import java.util.Objects;

/**
 * Represents a customer name information
 */
public class Name {
  private String firstName;
  private String lastName;

  /**
   * Constructs a new Name object
   * @param firstName a String represents the customer's first name
   * @param lastName a String represents the customer's last name
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   *
   * @return a String represents the customer's first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Set the customer's first name
   * @param firstName a String represents the customer's first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   *
   * @return a String represents the customer's last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Set the customer's last name
   * @param lastName a String represents the customer's last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   *
   * @param o another Name object
   * @return true if the two objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Name name = (Name) o;
    return Objects.equals(firstName, name.firstName) && Objects.equals(lastName,
        name.lastName);
  }

  /**
   *
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  /**
   *
   * @return a String represents the Name object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Name{");
    sb.append("firstName='").append(firstName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
