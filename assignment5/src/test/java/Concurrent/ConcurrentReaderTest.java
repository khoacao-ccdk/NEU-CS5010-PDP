package Concurrent;

import static org.junit.jupiter.api.Assertions.*;

import Common.Reader;
import Common.StudentClickInformation;
import Common.Writer;
import Concurrent.HighActivity.CourseActivity;
import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcurrentReaderTest {
  ConcurrentReader r1, r2;
  String p1, p2;
  BlockingQueue<StudentClickInformation> q1;
  BlockingQueue<ConcurrentCourse> q2;

  @BeforeEach
  void SetUp(){
    p1 = getClass().getResource("../studentVleSmall.csv").getPath();
    q1 = new LinkedBlockingQueue<>();
    r1 = new ConcurrentReader<>(
        p1,
        q1,
        StudentClickInformation.class,
        1
    );

    p2 = new File(ConcurrentWriter.OUTPUT_FOLDER_NAME + "/AAA_2013J.csv").getAbsolutePath();
    q2 = new LinkedBlockingQueue<>();
    r2 = new ConcurrentReader<>(
        p2,
        q2,
        CourseActivity.class,
        1
    );
  }

  @Test
  void getCourses(){
    ConcurrentMap<String, ConcurrentCourse> courseMap = new ConcurrentHashMap<>();
    assertDoesNotThrow(() -> ConcurrentReader.getCourses(courseMap));
    assertEquals(22, courseMap.size());
  }

  @Test
  void getResultFiles() throws Exception {
    List<File> expected = ConcurrentReader.getResultFiles();
    assertEquals(22, expected.size());
  }

  @Test
  void nextLine() throws Exception {
    assertEquals(null, r1.nextLine());
  }

  @Test
  void run() {
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
    assertDoesNotThrow(() -> {
      t1.join();
      t2.join();
    });
    assertTrue(q1.contains(ConcurrentReader.STUDENT_CLICK_POISON_PILL));
    assertTrue(q2.contains(ConcurrentReader.COURSE_ACTIVITY_POISON_PILL));
  }

  @Test
  void testEquals() {
    assertEquals(r1, r1);
    assertEquals(r1, new ConcurrentReader<>(
        p1,
        q1,
        StudentClickInformation.class,
        1
    ));

    assertNotEquals(r1, null);
    assertNotEquals(r1, new String());
    assertNotEquals(r1, new ConcurrentReader<>(
        p2,
        q1,
        StudentClickInformation.class,
        1
    ));
    assertNotEquals(r1, new ConcurrentReader<>(
        p1,
        q2,
        StudentClickInformation.class,
        1
    ));
    assertNotEquals(r1, new ConcurrentReader<>(
        p1,
        q1,
        CourseActivity.class,
        1
    ));
    assertNotEquals(r1, new ConcurrentReader<>(
        p1,
        q1,
        StudentClickInformation.class,
        2
    ));
  }

  @Test
  void testHashCode() {
    assertEquals(new ConcurrentReader<>(
        p1,
        q1,
        StudentClickInformation.class,
        1
    ).hashCode(),
        r1.hashCode());
  }
}