import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {
  Name name;
  @BeforeEach
  void setUp() {
    name = new Name("Cody", "C", "Cao");
  }

  @Test
  void getFirstName() {
    assertSame("Cody", name.getFirstName());
  }

  @Test
  void setFirstName() {
    name.setFirstName("Khoa");
    assertSame("Khoa", name.getFirstName());
  }

  @Test
  void getMiddleName() {
    assertSame("C", name.getMiddleName());
  }

  @Test
  void setMiddleName() {
    name.setMiddleName("Cu");
    assertSame("Cu", name.getMiddleName());
  }

  @Test
  void getLastName() {
    assertSame("Cao", name.getLastName());
  }

  @Test
  void setLastName() {
    name.setLastName("Last");
    assertSame("Last", name.getLastName());
  }

  @Test
  void testEquals() {
    assertTrue(name.equals(new Name("Cody", "C", "Cao")));
    assertFalse(name.equals(new Name("Incorrect", "", "Name")));
    assertFalse(name.equals(null));
  }

  @Test
  void testHashCode(){
    assertEquals(new Name("Cody", "C", "Cao"), name);
  }
}