package Problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseballPlayerTest {

  BaseballPlayer a1, a2;
  Name n1, n2;

  @BeforeEach
  void setUp() {
    n1 = new Name("Cody", "", "Cao");
    n2 = new Name("Khoa", "", "Cao");
    a1 = new BaseballPlayer(n1, 5.10, 179.5, "N/A", "Yankees", 10, 20);
    a2 = new BaseballPlayer(n2, 5.10, 185.5, "Yankees", 15, 25);
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
  void getTeam() {
    assertSame("Yankees", a1.getTeam());
  }

  @Test
  void getBatting() {
    assertEquals(10, a1.getBatting());
  }

  @Test
  void getNumHomeRun() {
    assertEquals(20, a1.getNumHomeRun());
  }

  @Test
  void equals() {
    BaseballPlayer test = new BaseballPlayer(n1, 5.10, 179.5, "N/A", "Yankees", 10, 20);
    assertTrue(a1.equals(a1));
    assertTrue(a1.equals(test));
    assertFalse(a1.equals(a2));
    assertFalse(a1.equals(null));
    assertFalse(a1.equals(new String()));
  }

  @Test
  void testHashCode() {
    assertEquals(new BaseballPlayer(n1, 5.10, 179.5, "N/A", "Yankees", 10, 20).hashCode(),
        a1.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("BaseballPlayer{");
    sb.append("team='").append("Yankees").append('\'');
    sb.append(", batting=").append(10.0);
    sb.append(", numHomeRun=").append(20);
    sb.append('}');
    assertTrue(sb.toString().equals(a1.toString()));
  }
}