import java.util.Objects;

/**
 * Represents a contact information of a tax filer
 *
 * @author Cody Cao
 */
public class ContactInfo {

  private Name name;
  private String address;
  private String phone;
  private String email;

  /**
   * Creates a ContactInfo object that holds contact information of a tax filer
   *
   * @param name    a Name object that holds tax filer's first and last name
   * @param address a String represents the tax filer's address
   * @param phone   a String represents the tax filer's phone
   * @param email   a String represents the tax filer's email
   */
  public ContactInfo(Name name, String address, String phone, String email) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  /**
   * @return a Name object that holds tax filer's name
   */
  public Name getName() {
    return name;
  }

  /**
   * Set tax filer's name
   *
   * @param name a Name object that holds tax filer's name
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * @return tax filer's address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Set tax filer's address
   *
   * @param address tax filer's address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return tax filer's phone number
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Set tax filer's phone number
   *
   * @param phone tax filer's phone number
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return tax filer's email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set tax filer's email address
   *
   * @param email tax filer's email address
   */
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactInfo that = (ContactInfo) o;
    return name.equals(that.name) && address.equals(that.address) && phone.equals(that.phone)
        && email.equals(that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, phone, email);
  }
}
