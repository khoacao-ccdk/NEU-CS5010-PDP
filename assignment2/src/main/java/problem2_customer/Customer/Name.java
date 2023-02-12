import java.util.Objects;

/**
 * Represents a customer's name object
 */
public class Name {

  private String firstName;
  private String lastName;

  /**
   * Creates a new Name object that holds customer's first and last name
   *
   * @param firstName a String represents the customer's first name
   * @param lastName  a String represents the customer's last name
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * @return a String represents the customer's first name
   */
  public String getFirstName() {
    return firstName;
  }


  /**
   * Set customer's first name
   *
   * @param firstName a String represents the customer's first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return a String represents the customer's last name
   */
  public String getLastName() {
    return lastName;
  }


  /**
   * Set customer's last name
   *
   * @param lastName a String represents the customer's last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @param o another name object
   * @return true if the two object are equal structurally
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
   * @return an int represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  /**
   *
   * @return a String represents the customer's name
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
