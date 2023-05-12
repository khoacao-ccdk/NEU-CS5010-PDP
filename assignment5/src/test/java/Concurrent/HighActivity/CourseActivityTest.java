package Concurrent.HighActivity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseActivityTest {
private CourseActivity a;
  @BeforeEach
  void setUp() {
    a=new CourseActivity("a",10,1000);
  }

  @Test
  void getCourseInfo() {
    Assertions.assertEquals("a",a.getCourseInfo());
  }

  @Test
  void getDate() {
    Assertions.assertEquals(10,a.getDate());
  }

  @Test
  void getNumClicks() {
    Assertions.assertEquals(1000,a.getNumClicks());
  }

  @Test
  void setCourseInfo() {
    a.setCourseInfo("s");
    Assertions.assertEquals("s",a.getCourseInfo());
  }

  @Test
  void setDate() {
    a.setDate(12);
    Assertions.assertEquals(12,a.getDate());
  }

  @Test
  void setNumClicks() {
    a.setNumClicks(122);
    Assertions.assertEquals(122,a.getNumClicks());
  }

  @Test
  void testEquals() {
    CourseActivity b=new CourseActivity("a",10,1000);
    CourseActivity b2=new CourseActivity("b",10,1000);
    Assertions.assertEquals(true,a.equals(b));
    Assertions.assertEquals(true,a.equals(a));
    Assertions.assertEquals(false,a.equals("a"));
    Assertions.assertEquals(false,a.equals(""));
    Assertions.assertEquals(false,a.equals(b2));
  }

  @Test
  void testHashCode() {
    CourseActivity b=new CourseActivity("a",10,1000);
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }

  @Test
  void testToString() {
    CourseActivity b=new CourseActivity("a",10,1000);
    Assertions.assertEquals(b.toString(),a.toString());
  }
}