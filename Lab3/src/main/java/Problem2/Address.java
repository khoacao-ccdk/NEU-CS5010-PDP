package Problem2;

import java.util.Objects;

/**
 * Class Address contains information about a restaurant's address, street and number, city, zip
 * code, state, and country
 */
public class Address {

  private String streetNumber;
  private String city;
  private String zip;
  private String state;
  private String country;

  /**
   * Construct a new Address object
   *
   * @param streetNumber restaurant's street and number, expressed as String
   * @param city         restaurant's city, expressed as String
   * @param zip          restaurant's zip code, expressed as String
   * @param state        restaurant's state, expressed as String
   * @param country      restaurant's country, expressed as String
   */
  public Address(String streetNumber, String city, String zip, String state, String country) {
    this.streetNumber = streetNumber;
    this.city = city;
    this.zip = zip;
    this.state = state;
    this.country = country;
  }

  /**
   * @return restaurant's street and number, expressed as String
   */
  public String getStreetNumber() {
    return streetNumber;
  }

  /**
   * Set restaurant's street and number
   *
   * @param streetNumber restaurant's street and number, expressed as String
   */
  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  /**
   * @return restaurant's city, expressed as String
   */
  public String getCity() {
    return city;
  }

  /**
   * Set restaurant's city
   *
   * @param city restaurant's city, expressed as String
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return restaurant's zip code, expressed as String
   */
  public String getZip() {
    return zip;
  }

  /**
   * Set restaurant's zip code
   *
   * @param zip restaurant's zip code, expressed as String
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   * @return restaurant's state, expressed as String
   */
  public String getState() {
    return state;
  }

  /**
   * Set restaurant's state
   *
   * @param state restaurant's state, expressed as String
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return restaurant's country, expressed as String
   */
  public String getCountry() {
    return country;
  }

  /**
   * Set restaurant's country
   *
   * @param country restaurant's country, expressed as String
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @param o another Address object
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
    Address address = (Address) o;
    return Objects.equals(streetNumber, address.streetNumber) && Objects.equals(
        city, address.city) && Objects.equals(zip, address.zip) && Objects.equals(
        state, address.state) && Objects.equals(country, address.country);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(streetNumber, city, zip, state, country);
  }

  /**
   * @return a String represents the address's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Address{");
    sb.append("streetNumber='").append(streetNumber).append('\'');
    sb.append(", city='").append(city).append('\'');
    sb.append(", zip='").append(zip).append('\'');
    sb.append(", state='").append(state).append('\'');
    sb.append(", country='").append(country).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
