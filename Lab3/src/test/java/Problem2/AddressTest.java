package Problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {
  Address a;
  @BeforeEach
  void setUp() {
    a = new Address("401 Terry Ave N",
        "Seattle",
        "98109",
        "WA",
        "USA");
  }
  @Test
  void getStreetNumber() {
    assertEquals("401 Terry Ave N", a.getStreetNumber());
  }

  @Test
  void setStreetNumber() {
    a.setStreetNumber("225 Terry Ave N");
    assertEquals("225 Terry Ave N", a.getStreetNumber());
  }

  @Test
  void getCity() {
    assertEquals("Seattle", a.getCity());
  }

  @Test
  void setCity() {
    a.setCity("Tacoma");
    assertEquals("Tacoma", a.getCity());
  }

  @Test
  void getZip() {
    assertEquals("98109", a.getZip());
  }

  @Test
  void setZip() {
    a.setZip("98121");
    assertEquals("98121", a.getZip());
  }

  @Test
  void getState() {
    assertEquals("WA", a.getState());
  }

  @Test
  void setState() {
    a.setState("OR");
    assertEquals("OR", a.getState());
  }

  @Test
  void getCountry() {
    assertEquals("USA", a.getCountry());
  }

  @Test
  void setCountry() {
    a.setCountry("Canada");
    assertEquals("Canada", a.getCountry());
  }

  @Test
  void testEquals() {
    Address equal = new Address("401 Terry Ave N",
        "Seattle",
        "98109",
        "WA",
        "USA");
    Address diff = new Address("225 Terry Ave N",
        "Tacoma",
        "98121",
        "ON",
        "Canada");
    Address allNull = new Address(null, null, null, null, null);

    assertEquals(a, a);
    assertEquals(a, equal);
    assertNotEquals(a, diff);
    assertNotEquals(a, null);
    assertNotEquals(a, allNull);
    assertNotEquals(a, new String());
  }

  @Test
  void testHashcode() {
    Address equal = new Address("401 Terry Ave N",
        "Seattle",
        "98109",
        "WA",
        "USA");
    Address diff = new Address("225 Terry Ave N",
        "Tacoma",
        "98121",
        "ON",
        "Canada");

    assertEquals(a.hashCode(), a.hashCode());
    assertEquals(equal.hashCode(), a.hashCode());
    assertNotEquals(diff.hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Address{");
    sb.append("streetNumber='").append("401 Terry Ave N").append('\'');
    sb.append(", city='").append("Seattle").append('\'');
    sb.append(", zip='").append("98109").append('\'');
    sb.append(", state='").append("WA").append('\'');
    sb.append(", country='").append("USA").append('\'');
    sb.append('}');

    assertEquals(sb.toString(), a.toString());
  }
}