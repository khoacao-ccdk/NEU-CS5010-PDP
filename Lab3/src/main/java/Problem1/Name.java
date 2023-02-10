package Problem1;

import java.util.Objects;

/**
 * Class Name contains information about a athlete's name, including their first, middle, and last
 * name
 */
public class Name {

  private String firstName, middleName, lastName;

  /**
   * Construct a new Name object
   *
   * @param firstName  athlete's first name, expressed as String
   * @param middleName athlete's middle name, expressed as String
   * @param lastName   athlete's last name, expressed as String
   */
  public Name(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * @return athlete's first name, expressed as String
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @return athlete's middle name, expressed as String
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * @return athlete's last name, expressed as String
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param o another Name object
   * @return true if two Name objects are equal structurally
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
    return firstName.equals(name.firstName) && middleName.equals(name.middleName)
        && lastName.equals(
        name.lastName);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
  }

  /**
   * @return a String contains object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Name{");
    sb.append("firstName='").append(firstName).append('\'');
    sb.append(", middleName='").append(middleName).append('\'');
    sb.append(", lastName='").append(lastName).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
