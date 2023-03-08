import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

  Customer customer;
  Name n;
  ContactInformation c;
  Address a;

  @BeforeEach
  void setUp() {
    a = new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109");
    c = new ContactInformation("1234561234", "1234564567", "test@gmail.com", "test.com");
    n = new Name("Cody", "Cao");
    customer = new Customer(n, "NEU", a, c);
  }

  @Test
  void getName() {
    assertEquals(new Name("Cody", "Cao"), customer.getName());
  }

  @Test
  void setName() {
    customer.setName(new Name("Khoa", "Cao"));
    assertEquals(new Name("Khoa", "Cao"), customer.getName());
  }

  @Test
  void getCompany() {
    assertEquals("NEU", customer.getCompany());
  }

  @Test
  void setCompany() {
    customer.setCompany("N/A");
    assertEquals("N/A", customer.getCompany());
  }

  @Test
  void getAddress() {
    assertEquals(new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109"),
        customer.getAddress());
  }

  @Test
  void setAddress() {
    customer.setAddress(new Address("2401 Terry Ave N", "Seattle", "King", "WA", "98109"));
    assertEquals(new Address("2401 Terry Ave N", "Seattle", "King", "WA", "98109"),
        customer.getAddress());
  }

  @Test
  void getContact() {
    assertEquals(new ContactInformation("1234561234", "1234564567", "test@gmail.com", "test.com"),
        customer.getContact());
  }

  @Test
  void setContact() {
    customer.setContact(
        new ContactInformation("1234561234", "1234564567", "test2@gmail.com", "test2.com"));
    assertEquals(new ContactInformation("1234561234", "1234564567", "test2@gmail.com", "test2.com"),
        customer.getContact());
  }

  @Test
  void testEquals() {
    assertEquals(customer, customer);
    assertEquals(new Customer(n, "NEU", a, c), customer);
    assertNotEquals(null, customer);
    assertNotEquals(new String(), customer);
    assertNotEquals(new Customer(null, "NEU", a, c), customer);
    assertNotEquals(new Customer(n, "", a, c), customer);
    assertNotEquals(new Customer(n, "NEU", null, c), customer);
    assertNotEquals(new Customer(n, "NEU", a, null), customer);
  }

  @Test
  void testHashCode() {
    assertEquals(new Customer(n, "NEU", a, c).hashCode(), customer.hashCode());
    assertNotEquals(new Customer(null, "NEU", a, c).hashCode(), customer.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Customer{");
    sb.append("name=").append(n);
    sb.append(", company='").append("NEU").append('\'');
    sb.append(", address=").append(a);
    sb.append(", contact=").append(c);
    sb.append('}');
    assertEquals(sb.toString(), customer.toString());
  }
}