package Sequential;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SequentialCourseTest {
private SequentialCourse a;
  @BeforeEach
  void setUp() {
    a=new SequentialCourse();
  }

  @Test
  void getClickRecord() {
    Assertions.assertEquals(null,a.getClickRecord());
  }

  @Test
  void testEquals() {
    SequentialCourse b=new SequentialCourse();
    Assertions.assertEquals(true,a.equals(a));
    Assertions.assertEquals(true,a.equals(b));
    Assertions.assertEquals(false,a.equals(""));
    Assertions.assertEquals(false,a.equals("a"));
  }

  @Test
  void testHashCode() {
    SequentialCourse b=new SequentialCourse();
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    SequentialCourse b=new SequentialCourse();
    Assertions.assertEquals(b.toString(),a.toString());
  }
}