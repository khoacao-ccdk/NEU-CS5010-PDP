import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactInformationTest {

  ContactInformation c;

  @BeforeEach
  void setUp() {
    c = new ContactInformation("1234561234", "1234564567", "test@gmail.com", "test.com");
  }

  @Test
  void getPhone1() {
    assertEquals("1234561234", c.getPhone1());
  }

  @Test
  void setPhone1() {
    c.setPhone1("1234");
    assertEquals("1234", c.getPhone1());
  }

  @Test
  void getPhone2() {
    assertEquals("1234564567", c.getPhone2());
  }

  @Test
  void setPhone2() {
    c.setPhone2("1234");
    assertEquals("1234", c.getPhone2());
  }

  @Test
  void getEmail() {
    assertEquals("test@gmail.com", c.getEmail());
  }

  @Test
  void setEmail() {
    c.setEmail("test2@gmail.com");
    assertEquals("test2@gmail.com", c.getEmail());
  }

  @Test
  void getWeb() {
    assertEquals("test.com", c.getWeb());
  }

  @Test
  void setWeb() {
    c.setWeb("test2.com");
    assertEquals("test2.com", c.getWeb());
  }

  @Test
  void testEquals() {
    assertEquals(c, c);
    assertEquals(new ContactInformation("1234561234", "1234564567", "test@gmail.com", "test.com"),
        c);
    assertNotEquals(null, c);
    assertNotEquals(new String(), c);
    assertNotEquals(new ContactInformation("", "1234564567", "test@gmail.com", "test.com"), c);
    assertNotEquals(new ContactInformation("1234561234", "", "test@gmail.com", "test.com"),
        c);
    assertNotEquals(new ContactInformation("1234561234", "1234564567", "", "test.com"),
        c);
    assertNotEquals(new ContactInformation("1234561234", "1234564567", "test@gmail.com", ""),
        c);
  }

  @Test
  void testHashCode() {
    assertEquals(
        new ContactInformation("1234561234", "1234564567", "test@gmail.com", "test.com").hashCode(),
        c.hashCode());
    assertNotEquals(
        new ContactInformation("", "1234564567", "test@gmail.com", "test.com").hashCode(),
        c.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("ContactInformation{");
    sb.append("phone1='").append("1234561234").append('\'');
    sb.append(", phone2='").append("1234564567").append('\'');
    sb.append(", email='").append("test@gmail.com").append('\'');
    sb.append(", web='").append("test.com").append('\'');
    sb.append('}');
    assertEquals(sb.toString(), c.toString());
  }
}