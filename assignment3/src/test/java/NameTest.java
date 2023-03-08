import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {
  Name n;
  @BeforeEach
  void setUp() {
    n = new Name("Cody", "Cao");
  }

  @Test
  void getFirstName() {
    assertEquals("Cody", n.getFirstName());
  }

  @Test
  void setFirstName() {
    n.setFirstName("Khoa");
    assertEquals("Khoa", n.getFirstName());
  }

  @Test
  void getLastName() {
    assertEquals("Cao", n.getLastName());
  }

  @Test
  void setLastName() {
    n.setLastName("C");
    assertEquals("C", n.getLastName());
  }

  @Test
  void testEquals() {
    assertEquals(n, n);
    assertEquals(new Name("Cody", "Cao"), n);
    assertNotEquals(n, null);
    assertNotEquals(n, new String());
    assertNotEquals(new Name("", "Cao"), n);
    assertNotEquals(new Name("Cody", ""), n);
  }

  @Test
  void testHashCode() {
    assertEquals(new Name("Cody", "Cao").hashCode(), n.hashCode());
    assertNotEquals(new Name("", "").hashCode(), n.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Name{");
    sb.append("firstName='").append("Cody").append('\'');
    sb.append(", lastName='").append("Cao").append('\'');
    sb.append('}');
    assertEquals(sb.toString(), n.toString());
  }
}