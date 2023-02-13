package Problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunnerTest {

  Runner a1, a2;
  Name n1, n2;

  @BeforeEach
  void setUp() {
    n1 = new Name("Cody", "", "Cao");
    n2 = new Name("Khoa", "", "Cao");
    a1 = new Runner(n1, 5.10, 179.5, "N/A", 10.5, 100.5, "All");
    a2 = new Runner(n2, 5.10, 185.5, 20.5, 120.5, "N/A");
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
  void getBest5KTime() {
    assertEquals(10.5, a1.getBest5KTime());
  }

  @Test
  void getBestHalfMarathonTime() {
    assertEquals(100.5, a1.getBestHalfMarathonTime());
  }

  @Test
  void getFavoriteRunningEvent() {
    assertSame("All", a1.getFavoriteRunningEvent());
  }

  @Test
  void equals() {
    Runner test = new Runner(n1, 5.10, 179.5, "N/A", 10.5, 100.5, "All");
    assertTrue(a1.equals(a1));
    assertTrue(a1.equals(test));
    assertFalse(a1.equals(a2));
    assertFalse(a1.equals(null));
    assertFalse(a1.equals(new String()));
  }

  @Test
  void testHashCode() {
    assertEquals(new Runner(n1, 5.10, 179.5, "N/A", 10.5, 100.5, "All").hashCode(),
        a1.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Runner{");
    sb.append("best5KTime=").append(10.5);
    sb.append(", bestHalfMarathonTime=").append(100.5);
    sb.append(", favoriteRunningEvent='").append("All").append('\'');
    sb.append('}');
    assertTrue(sb.toString().equals(a1.toString()));
  }
}