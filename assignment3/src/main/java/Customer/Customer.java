import java.util.Objects;

/**
 * Represents a customer's information
 *
 * @author Cody Cao
 */
public class Customer {

  private Name name;
  private String company;
  private Address address;
  private ContactInformation contact;

  /**
   * Constructs a new Customer object
   *
   * @param name    a Name object represents the customer's name
   * @param company a String represents the customer's company
   * @param address an Address object represents the customer's address
   * @param contact a ContactInformation object represents the customer's contact information
   */
  public Customer(Name name, String company, Address address, ContactInformation contact) {
    this.name = name;
    this.company = company;
    this.address = address;
    this.contact = contact;
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
   * @return a String represents the customer's company
   */
  public String getCompany() {
    return company;
  }

  /**
   * Set the customer's company
   *
   * @param company a String represents the customer's company
   */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * @return an Address object represents the customer's address
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Set the customer's address
   *
   * @param address an Address object represents the customer's address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * @return a ContactInformation object represents the customer's contact information
   */
  public ContactInformation getContact() {
    return contact;
  }

  /**
   * Set the customer's contact information
   *
   * @param contact a ContactInformation object represents the customer's contact information
   */
  public void setContact(ContactInformation contact) {
    this.contact = contact;
  }

  /**
   * @param o another Customer object
   * @return true if the two customer objects are equal structurally, false otherwise
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
    return Objects.equals(name, customer.name) && Objects.equals(company,
        customer.company) && Objects.equals(address, customer.address)
        && Objects.equals(contact, customer.contact);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, company, address, contact);
  }

  /**
   * @return a String represents the Customer object's iformation
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Customer{");
    sb.append("name=").append(name);
    sb.append(", company='").append(company).append('\'');
    sb.append(", address=").append(address);
    sb.append(", contact=").append(contact);
    sb.append('}');
    return sb.toString();
  }
}
