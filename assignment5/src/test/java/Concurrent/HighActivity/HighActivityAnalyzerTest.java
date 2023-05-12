package Concurrent.HighActivity;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HighActivityAnalyzerTest {
  private CourseActivity c;
  private HighActivityAnalyzer a;
  private Set<CourseActivity> rs;
  private BlockingQueue<CourseActivity> cb;
  @BeforeEach
  void setUp() {
    c=new CourseActivity("a",10,1000);
    rs=new HashSet<>();
    cb=new LinkedBlockingQueue<>();
    a=new HighActivityAnalyzer(100,cb,rs,1);
  }

  @Test
  void getHighActivityCourses() throws Exception {
    HighActivityAnalyzer.getHighActivityCourses(1000,4);
    Assertions.assertEquals(true,new File("ConcurrentResult/activity-1000.csv").exists());
  }

  @Test
  void analyze() {
    a.analyze(c);
    Assertions.assertEquals(c,rs.iterator().next());
  }



  @Test
  void testEquals() {
    HighActivityAnalyzer b=new HighActivityAnalyzer(100,cb,rs,1);
    HighActivityAnalyzer b2=new HighActivityAnalyzer(10,cb,rs,1);
    Assertions.assertEquals(true,a.equals(a));
    Assertions.assertEquals(true,a.equals(b));
    Assertions.assertEquals(false,a.equals("a"));
    Assertions.assertEquals(false,a.equals(""));
    Assertions.assertEquals(false,a.equals(b2));
  }

  @Test
  void testHashCode() {
    HighActivityAnalyzer b=new HighActivityAnalyzer(100,cb,rs,1);
    Assertions.assertEquals(b.hashCode(),a.hashCode());
  }
}