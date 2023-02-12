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
    assertSame("Cody", n.getFirstName());
  }

  @Test
  void setFirstName() {
    n.setFirstName("Khoa");
    assertSame("Khoa", n.getFirstName());
  }

  @Test
  void getLastName() {
    assertSame("Cao", n.getLastName());
  }

  @Test
  void setLastName() {
    n.setLastName("Test");
    assertSame("Test", n.getLastName());
  }

  @Test
  void testEquals() {
    Name test = new Name("Cody", "Cao");
    Name nullName = new Name(null, null);
    assertTrue(n.equals(test));
    assertTrue(n.equals(n));
    assertFalse(n.equals(null));
    assertFalse(n.equals(new String()));
    assertNotEquals(n, nullName);
  }

  @Test
  void testHashCode() {
    Name test = new Name("Cody", "Cao");
    assertEquals(test.hashCode(), n.hashCode());
  }

  @Test
  void testToString(){
    StringBuffer sb = new StringBuffer("Name{");
    sb.append("firstName='").append("Cody").append('\'');
    sb.append(", lastName='").append("Cao").append('\'');
    sb.append('}');
  }
}