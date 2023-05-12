package Customer;

import java.util.Objects;

/**
 * Represents a member's address information
 *
 * @author Cody Cao
 */
public class Address {

  private String address;
  private String city;
  private String county;
  private String state;
  private String zip;

  /**
   * Constructs a new Address object
   *
   * @param address a String represents the address' street and number
   * @param city    a String represents the address' city
   * @param county  a String represents the address' county
   * @param state   a String represents the address' state
   * @param zip     a String represents the address' zip code
   */
  public Address(String address, String city, String county, String state, String zip) {
    this.address = address;
    this.city = city;
    this.county = county;
    this.state = state;
    this.zip = zip;
  }

  /**
   * @return a String represents the address' street and number
   */
  public String getAddress() {
    return address;
  }

  /**
   * Set the address' street and number
   *
   * @param address a String represents the address' street and number
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * @return a String represents the address' city
   */
  public String getCity() {
    return city;
  }

  /**
   * Set the address' city
   *
   * @param city a String represents the address' city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return a String represents the address' county
   */
  public String getCounty() {
    return county;
  }

  /**
   * Set the address' county
   *
   * @param county a String represents the address' county
   */
  public void setCounty(String county) {
    this.county = county;
  }

  /**
   * @return a String represents the address' state
   */
  public String getState() {
    return state;
  }

  /**
   * Set the address' state
   *
   * @param state a String represents the address' state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return a String represents the address' zip code
   */
  public String getZip() {
    return zip;
  }

  /**
   * Set the address' zip code
   *
   * @param zip a String represents the address' zip code
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * @param o another Address object
   * @return true if the two Address objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address1 = (Address) o;
    return Objects.equals(address, address1.address) && Objects.equals(city,
        address1.city) && Objects.equals(county, address1.county)
        && Objects.equals(state, address1.state) && Objects.equals(zip,
        address1.zip);
  }

  /**
   * @return an Integer represents the Address' hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, city, county, state, zip);
  }

  /**
   *
   * @return a String represents the member's address information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Address{");
    sb.append("address='").append(address).append('\'');
    sb.append(", city='").append(city).append('\'');
    sb.append(", county='").append(county).append('\'');
    sb.append(", state='").append(state).append('\'');
    sb.append(", zip='").append(zip).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
