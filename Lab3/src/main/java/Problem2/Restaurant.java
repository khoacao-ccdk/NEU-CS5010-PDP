package Problem2;

import java.util.Objects;

/**
 * Class Menu contains information about a restaurant, including address, menu, and whether the
 * restaurant is open/close
 */
public class Restaurant {

  private Address address;
  private Menu menu;
  private boolean isOpen;

  /**
   * Construct a new Restaurant object
   *
   * @param address restaurant's address expressed as an Address object
   * @param menu    restaurant's menu expressed as a Menu object
   * @param isOpen  whether the restaurant is open/closed expressd by a Boolean
   */
  public Restaurant(Address address, Menu menu, boolean isOpen) {
    this.address = address;
    this.menu = menu;
    this.isOpen = isOpen;
  }

  /**
   * @return an Address object with the restaurant's address information
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Set restaurant's address
   *
   * @param address an Address object with the restaurant's address information
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * @return a Menu object with the restaurant's menu information
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Set the restaurant's menu
   *
   * @param menu a Menu object with the restaurant's menu information
   */
  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  /**
   * @return a Boolean indicating if the restaurant is open/closed
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Set restaurant's open status
   *
   * @param open a Boolean indicating if the restaurant is open/closed
   */
  public void setOpen(boolean open) {
    isOpen = open;
  }

  /**
   * @param o another Restaurant object
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
    Restaurant that = (Restaurant) o;
    return isOpen == that.isOpen && Objects.equals(address, that.address)
        && Objects.equals(menu, that.menu);
  }

  /**
   * @return an Integer represents the restaurant's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, menu, isOpen);
  }

  /**
   * @return a String with the restaurant's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Restaurant{");
    sb.append("address=").append(address);
    sb.append(", menu=").append(menu);
    sb.append(", isOpen=").append(isOpen);
    sb.append('}');
    return sb.toString();
  }
}
