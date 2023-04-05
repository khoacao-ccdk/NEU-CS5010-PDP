import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

  Address a;

  @BeforeEach
  void beforeEach() {
    a = new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109");
  }

  @Test
  void getAddress() {
    assertEquals("225 Terry Ave N", a.getAddress());
  }

  @Test
  void setAddress() {
    a.setAddress("401 Terry Ave N");
    assertEquals("401 Terry Ave N", a.getAddress());
  }

  @Test
  void getCity() {
    assertEquals("Seattle", a.getCity());
  }

  @Test
  void setCity() {
    a.setCity("N/A");
    assertEquals("N/A", a.getCity());
  }

  @Test
  void getCounty() {
    assertEquals("King", a.getCounty());
  }

  @Test
  void setCounty() {
    a.setCounty("N/A");
    assertEquals("N/A", a.getCounty());
  }

  @Test
  void getState() {
    assertEquals("WA", a.getState());
  }

  @Test
  void setState() {
    a.setState("N/A");
    assertEquals("N/A", a.getState());
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
  void testEquals() {
    assertEquals(a, a);
    assertEquals(new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109"), a);
    assertNotEquals(a, null);
    assertNotEquals(new String(), a);
    assertNotEquals(new Address("", "Seattle", "King", "WA", "98109"), a);
    assertNotEquals(new Address("225 Terry Ave N", "", "King", "WA", "98109"), a);
    assertNotEquals(new Address("225 Terry Ave N", "Seattle", "", "WA", "98109"), a);
    assertNotEquals(new Address("225 Terry Ave N", "Seattle", "King", "", "98109"), a);
    assertNotEquals(new Address("225 Terry Ave N", "Seattle", "King", "WA", ""), a);
  }

  @Test
  void testHashCode() {
    assertEquals(new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109").hashCode(),
        a.hashCode());
    assertNotEquals(new Address("", "Seattle", "King", "WA", "980109").hashCode(), a.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Address{");
    sb.append("address='").append("225 Terry Ave N").append('\'');
    sb.append(", city='").append("Seattle").append('\'');
    sb.append(", county='").append("King").append('\'');
    sb.append(", state='").append("WA").append('\'');
    sb.append(", zip='").append("98109").append('\'');
    sb.append('}');
    assertEquals(sb.toString(), a.toString());
  }
}