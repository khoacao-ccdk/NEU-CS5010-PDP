package Concurrent;

import static org.junit.jupiter.api.Assertions.*;

import Common.Course;
import Concurrent.HighActivity.CourseActivity;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcurrentWriterTest {

  ConcurrentMap<String, ConcurrentCourse> courseMap;
  ConcurrentMap<Integer, Long> clickData;
  ConcurrentCourse c1, c2;
  ConcurrentWriter w1, w2, w3, w4;

  @BeforeEach
  void setUp() {
    courseMap = new ConcurrentHashMap<>();
    clickData = new ConcurrentHashMap<>();

    clickData.putIfAbsent(10, -100L);
    c1 = new ConcurrentCourse(
        "Test",
        "%01",
        clickData
    );
    courseMap.putIfAbsent("Test_%01", c1);

    w1 = new ConcurrentWriter(
        "concurrent-test-output_course",
        new ConcurrentCourse(),
        ConcurrentCourse.class
    );

    w2 = new ConcurrentWriter("test-create");
  }

  @Test
  void writeSummaryFile() {
    assertDoesNotThrow(() -> ConcurrentWriter.writeSummaryFile(1, courseMap));
    File file = new File("ConcurrentResult", "Test_%01.csv");
    assertTrue(file.exists());

    try (
        Reader reader = Files.newBufferedReader(file.toPath());
        CSVReader csvReader = new CSVReader(reader);
    ) {
      csvReader.skip(1);

      //Check if there's content in the file
      List<String[]> content = csvReader.readAll();
      assertEquals("10", content.get(0)[0]);
      assertEquals("-100", content.get(0)[1]);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void writeHighActivityFile() {
    Set<CourseActivity> testSet = new HashSet<>();
    testSet.add(new CourseActivity("Test_01", 10, 110));

    assertDoesNotThrow(() -> ConcurrentWriter.writeHighActivityFile(100, testSet));
    File file = new File("ConcurrentResult", "activity-100.csv");
    assertTrue(file.exists());

    try (
        Reader reader = Files.newBufferedReader(file.toPath());
        CSVReader csvReader = new CSVReader(reader);
    ) {
      csvReader.skip(1);
      //Check if there's content in the file
      List<String[]> content = csvReader.readAll();
      assertEquals("Test_01", content.get(0)[0]);
      assertEquals("10", content.get(0)[1]);
      assertEquals("110", content.get(0)[2]);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void createFile() {
    assertDoesNotThrow(() -> w2.createFile("test-create"));
    File file = new File("ConcurrentResult", "test-create.csv");
    assertTrue(file.exists());
  }

  @Test
  void writeToFile() {
    assertDoesNotThrow(() -> w2.writeToFile("test-create", new String[]{"Test", "File"}, false
    ));
    File file = new File("ConcurrentResult", "test-create.csv");
    try (
        Reader reader = Files.newBufferedReader(file.toPath());
        CSVReader csvReader = new CSVReader(reader);
    ) {
      //Check if there's content in the file
      List<String[]> content = csvReader.readAll();
      assertEquals("Test", content.get(0)[0]);
      assertEquals("File", content.get(0)[1]);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void run() {
    c2 = new ConcurrentCourse(
        "Test",
        "%02",
        clickData
    );
    w3 = new ConcurrentWriter(
        "Test_%02",
        c2,
        Course.class
    );

    Set<CourseActivity> testSet = new HashSet<>();
    testSet.add(new CourseActivity("Test_01", 10, 110));
    testSet.add(new CourseActivity("Test_02", 0, 120));

    w4 = new ConcurrentWriter(
        "concurrent-test-output_high-activity",
        testSet,
        CourseActivity.class
    );

    Thread t1 = new Thread(w3);
    Thread t2 = new Thread(w4);
    t1.start();
    t2.start();
    assertDoesNotThrow(() -> {
      t1.join();
      t2.join();
    });

    File f1 = new File("ConcurrentResult", "Test_%02.csv");
    File f2 = new File("ConcurrentResult", "concurrent-test-output_high-activity.csv");

    assertTrue(f1.exists());
    assertTrue(f2.exists());
  }

  @Test
  void testEquals() {
    assertEquals(w1, w1);
    assertEquals(w1, new ConcurrentWriter(
        "concurrent-test-output_course",
        new ConcurrentCourse(),
        ConcurrentCourse.class
    ));

    assertNotEquals(w1, null);
    assertNotEquals(w1, new String());
    assertNotEquals(w1, new ConcurrentWriter(
        "concurrent-test-output_course-1",
        new ConcurrentCourse(),
        ConcurrentCourse.class
    ));
    assertNotEquals(w1, new ConcurrentWriter(
        "concurrent-test-output_course",
        null,
        ConcurrentCourse.class
    ));
    assertNotEquals(w1, new ConcurrentWriter(
        "concurrent-test-output_course",
        new ConcurrentCourse(),
        null
    ));
  }

  @Test
  void testHashCode() {
    assertEquals(new ConcurrentWriter(
        "concurrent-test-output_course",
        new ConcurrentCourse(),
        ConcurrentCourse.class
    ).hashCode(), w1.hashCode());
  }
}