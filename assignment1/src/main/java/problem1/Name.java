package problem1;

import java.util.Objects;

/**
 * Represents a customer's name
 *
 * @author Cody Cao
 */
public class Name {

  private String firstName;
  private String middleName;
  private String lastName;

  /**
   * Creates a Name object that holds information about a frequent flyer's name
   *
   * @param firstName  a String represents the frequent flyer's first name
   * @param middleName a String represents the frequent flyer's middle name
   * @param lastName   a String represents the frequent flyer's last name
   */
  public Name(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * @return FrequentFlyer's first name
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Update FrequentFlyer's first name
   *
   * @param firstName new middle name to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return FrequentFlyer's middle name
   */
  public String getMiddleName() {
    return this.middleName;
  }

  /**
   * Update FrequentFlyer's middle name
   *
   * @param middleName new middle name to set
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * @return FrequentFlyer's last name
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Update FrequentFlyer's last name
   *
   * @param lastName new middle name to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Check if two Name objects represent the identical name of a frequent flyer
   * @param o another Name object
   * @return true if the two Name objects represent an identical name and false otherwise
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
    return firstName.equals(name.firstName) &&
        middleName.equals(name.middleName) &&
        lastName.equals(name.lastName);
  }

  /**
   *
   * @return an int hashed representative of the Name object
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
  }
}
