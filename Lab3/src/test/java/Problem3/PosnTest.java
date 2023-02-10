package Problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PosnTest {

  Posn p1, p2, p3, p4, p5;

  @BeforeEach
  void setUp() {
    p1 = new Posn(10, 20);
    p2 = new Posn(10, 20);
    p3 = new Posn(15, 30);
    p4 = new Posn(null, null);
  }

  @Test
  void testEquals() {
    Posn test = new Posn(10, 20);
    assertTrue(p1.equals(p1));
    assertTrue(p1.equals(p2) && p2.equals(p1));
    assertTrue(p1.equals(p2) && p2.equals(test) &&
        p1.equals(test));
    assertTrue(!p1.equals(p3) && !p1.equals(p3));
    assertFalse(p1.equals(null));
    assertFalse(p1.equals(new String()));
    assertFalse(p1.equals(p4));
    assertFalse(p4.equals(p1));
  }

  @Test
  void testHashCode() {
    assertEquals(p1.hashCode(), p1.hashCode());
    assertTrue(p1.equals(p2) &&
        p1.hashCode() == p2.hashCode());
    assertTrue(!p1.equals(p3) &&
        p1.hashCode() != p3.hashCode());
    assertTrue(!p1.equals(p4) &&
        p1.hashCode() != p4.hashCode());
  }
}