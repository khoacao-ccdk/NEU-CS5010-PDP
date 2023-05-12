package Concurrent;

import static org.junit.jupiter.api.Assertions.*;

import Common.Reader;
import Common.StudentClickInformation;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcurrentAnalyzerTest {
  ConcurrentAnalyzer a;
  BlockingQueue<StudentClickInformation> q;
  ConcurrentMap<String, ConcurrentCourse> m;

  @BeforeEach
  void setUp() {
    q = new ArrayBlockingQueue<>(2);
    m = new ConcurrentHashMap<>();

    m.putIfAbsent("Test_01", new ConcurrentCourse(
        "Test",
        "01",
        new ConcurrentHashMap<>()
    ));

    a = new ConcurrentAnalyzer(
        q,
        m
    );
  }

  @Test
  void analyze() {
    int numThreads = Runtime.getRuntime().availableProcessors();
    try {
      ConcurrentReader.getCourses(m);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }

    //Start Analyzing
    ConcurrentAnalyzer.analyze(numThreads, m);

    assertEquals(2836,
        m.getOrDefault("AAA_2013J", null)
        .getClickRecord()
        .getOrDefault(-1, (long)0)
    );
  }

  @Test
  void updateCourse() {
    a.updateCourse(new StudentClickInformation(
       "Test",
       "01",
       1,
        10
    ));

    assertEquals(10, m.getOrDefault("Test_01", null)
        .getClickRecord()
        .getOrDefault(1, (long)0)
        );
  }

  @Test
  void run() {
    try {
      q.put(new StudentClickInformation(
          "Test",
          "01",
          1,
          10
      ));
      q.put(ConcurrentReader.STUDENT_CLICK_POISON_PILL);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Thread t = new Thread(
        new ConcurrentAnalyzer(q, m)
    );

    t.start();
    assertDoesNotThrow(() -> t.join());

    assertEquals(10, m.getOrDefault("Test_01", null)
        .getClickRecord()
        .getOrDefault(1, (long)0)
    );
  }

  @Test
  void testEquals() {
    assertEquals(a, a);
    assertEquals(a, new ConcurrentAnalyzer(q, m));
    assertNotEquals(a, new String());
    assertNotEquals(a, null);
    assertNotEquals(a, new ConcurrentAnalyzer(q, null));
    assertNotEquals(a, new ConcurrentAnalyzer(null, m));
  }

  @Test
  void testHashCode() {
    assertEquals(a.hashCode(), new ConcurrentAnalyzer(q, m).hashCode());
  }
}