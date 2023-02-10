package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {

  Name name;

  @BeforeEach
  void setUp() {
    this.name = new Name("Cody", "Cao");
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
  void getLastName() {
    assertSame("Cao", name.getLastName());
  }

  @Test
  void setLastName() {
    name.setLastName("C");
    assertSame("C", name.getLastName());
  }

  @Test
  void equals() {
    assertTrue(new Name("Cody", "Cao").equals(name));
  }

  @Test
  void equalsNullFields(){
    assertFalse(name.equals(null));
    assertFalse(name.equals(new Name(
        null, null
    )));
  }

  @Test
  void testHashCode(){
    assertEquals(new Name("Cody", "Cao").hashCode(), name.hashCode());
  }
}