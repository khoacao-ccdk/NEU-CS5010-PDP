package Problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {

  Name n;

  @BeforeEach
  void setUp() {
    n = new Name("Cody", "C", "Cao");
  }

  @Test
  void getFirstName() {
    assertSame("Cody", n.getFirstName());
  }

  @Test
  void getMiddleName() {
    assertSame("C", n.getMiddleName());
  }

  @Test
  void getLastName() {
    assertSame("Cao", n.getLastName());
  }

  @Test
  void equals() {
    Name test = new Name("Cody", "C", "Cao");
    assertTrue(n.equals(test));
    assertFalse(n.equals(new Name("", "", "")));
    assertFalse(n.equals(null));
    assertFalse(n.equals(new String()));
  }

  @Test
  void testHashCode() {
    Name test = new Name("Cody", "C", "Cao");
    assertEquals(test.hashCode(), n.hashCode());
  }

  @Test
  void testToString() {
    String test = new StringBuffer("Name{")
        .append("firstName='").append("Cody").append('\'')
        .append(", middleName='").append("C").append('\'')
        .append(", lastName='").append("Cao").append('\'')
        .append('}').toString();
    assertTrue(n.toString().equals(test));
  }
}