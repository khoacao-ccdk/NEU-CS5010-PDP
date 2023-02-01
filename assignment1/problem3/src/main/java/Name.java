import java.util.Objects;

/**
 * Represents a name information of a tax filer
 *
 * @author Cody Cao
 */
public class Name {

  private String firstName;
  private String lastName;

  /**
   * Creates a Name object that holds name information of a tax filer
   *
   * @param firstName a String represents a tax filer's first name
   * @param lastName  a String represents a tax filer's last name
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * @return tax filer's first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Set a tax filer's first name
   *
   * @param firstName tax filer's first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return tax filer's last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Set a tax filer's last name
   *
   * @param lastName tax filer's last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   *
   * @param o another Name object
   * @return true if the two Name objects represent the same name, false otherwise
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
    return firstName.equals(name.firstName) && lastName.equals(name.lastName);
  }

  /**
   *
   * @return the hashed version of the Name object
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
