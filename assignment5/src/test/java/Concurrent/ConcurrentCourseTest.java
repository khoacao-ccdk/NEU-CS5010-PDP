package Concurrent;

import static org.junit.jupiter.api.Assertions.*;

import Common.Course;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcurrentCourseTest {

  Course c;
  ConcurrentMap<Integer, Long> m;

  @BeforeEach
  void setUp() {
    m = new ConcurrentHashMap<>();
    c = new ConcurrentCourse("Test", "01", m);
  }

  @Test
  void getCodeModule() {
    assertEquals("Test", c.getCodeModule());
  }

  @Test
  void setCodeModule() {
    c.setCodeModule("");
    assertEquals("", c.getCodeModule());
  }

  @Test
  void getCodePresentation() {
    assertEquals("01", c.getCodePresentation());
  }

  @Test
  void setCodePresentation() {
    c.setCodePresentation("0");
    assertEquals("0", c.getCodePresentation());
  }

  @Test
  void getClickRecord() {
    assertEquals(m, c.getClickRecord());
  }

  @Test
  void testEquals() {
    assertEquals(c, new ConcurrentCourse("Test", "01", m));
    assertEquals(c, c);
    assertNotEquals(c, new String());
    assertNotEquals(c, null);
    assertNotEquals(c, new ConcurrentCourse("", "01", m));
    assertNotEquals(c, new ConcurrentCourse("Test", "", m));
    assertNotEquals(c, new ConcurrentCourse("Test", "01", null));
  }

  @Test
  void testHashCode() {
    assertEquals(new ConcurrentCourse("Test", "01", m).hashCode(),
        c.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Course{");
    sb.append("codeModule='").append("Test").append('\'');
    sb.append(", codePresentation='").append("01").append('\'');
    sb.append(", clickRecord=").append(m);
    sb.append('}');

    assertEquals(sb.toString(), c.toString());
  }
}