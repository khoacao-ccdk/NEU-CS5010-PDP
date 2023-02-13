package Problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AthleteTest {

  Athlete a1, a2;
  Name n1, n2;

  @BeforeEach
  void setUp() {
    n1 = new Name("Cody", "", "Cao");
    n2 = new Name("Khoa", "", "Cao");
    a1 = new Athlete(n1, 5.10, 179.5, "N/A");
    a2 = new Athlete(n2, 5.10, 185.5);
  }

  @Test
  void getAthletesName() {
    assertSame(n1, a1.getAthletesName());
  }

  @Test
  void getHeight() {
    assertEquals(5.10, a1.getHeight());
  }

  @Test
  void getWeight() {
    assertEquals(179.5, a1.getWeight());
  }

  @Test
  void getLeague() {
    assertSame("N/A", a1.getLeague());
    assertSame(null, a2.getLeague());
  }

  @Test
  void equals() {
    Athlete test = new Athlete(n1, 5.10, 179.5, "N/A");
    assertTrue(a1.equals(a1));
    assertTrue(a1.equals(test));
    assertFalse(a1.equals(a2));
    assertFalse(a1.equals(null));
    assertFalse(a1.equals(new String()));
  }

  @Test
  void testHashCode() {
    assertEquals(new Athlete(n1, 5.10, 179.5, "N/A").hashCode(),
        a1.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Athlete{");
    sb.append("athletesName=").append(n1);
    sb.append(", height=").append(5.10);
    sb.append(", weight=").append(179.5);
    sb.append(", league='").append("N/A").append('\'');
    sb.append('}');
    assertTrue(sb.toString().equals(a1.toString()));
  }
}