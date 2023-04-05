import java.util.Objects;

/**
 * Represents a member's contact information
 * @author Cody Cao
 */
public class ContactInformation {
  private String phone1;
  private String phone2;
  private String email;
  private String web;

  /**
   * Constructs a new ContactInformation object
   * @param phone1 a String represents the member's first phone number
   * @param phone2 a String represents the member's second phone number
   * @param email a String represents the member's email address
   * @param web a String represents the member's website
   */
  public ContactInformation(String phone1, String phone2, String email, String web) {
    this.phone1 = phone1;
    this.phone2 = phone2;
    this.email = email;
    this.web = web;
  }

  /**
   *
   * @return a String represents the member's first phone number
   */
  public String getPhone1() {
    return phone1;
  }

  /**
   * Set member's first phone number
   * @param phone1 a String represents the member's first phone number
   */
  public void setPhone1(String phone1) {
    this.phone1 = phone1;
  }

  /**
   *
   * @return a String represents the member's second phone number
   */
  public String getPhone2() {
    return phone2;
  }

  /**
   * Set the member's second phone number
   * @param phone2 a String represents the member's second phone number
   */
  public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }

  /**
   *
   * @return a String represents the member's email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set the member's email address
   * @param email a String represents the member's email address
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   *
   * @return a String represents the member's website
   */
  public String getWeb() {
    return web;
  }

  /**
   * Set the member's website
   * @param web a String represents the member's website
   */
  public void setWeb(String web) {
    this.web = web;
  }

  /**
   *
   * @param o another Address object
   * @return true if the two Address object are equal structurally
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactInformation that = (ContactInformation) o;
    return Objects.equals(phone1, that.phone1) && Objects.equals(phone2,
        that.phone2) && Objects.equals(email, that.email) && Objects.equals(web,
        that.web);
  }

  /**
   *
   * @return an Integer represents the Address object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(phone1, phone2, email, web);
  }

  /**
   *
   * @return a String represents the member's contact information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ContactInformation{");
    sb.append("phone1='").append(phone1).append('\'');
    sb.append(", phone2='").append(phone2).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append(", web='").append(web).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
