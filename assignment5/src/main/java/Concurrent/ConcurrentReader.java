package Concurrent;

import Common.Reader;
import Common.StudentClickInformation;
import Concurrent.HighActivity.CourseActivity;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

/**
 * A Reader class that can be run in parallel
 *
 * @author Cody Cao, Letian Shi
 */
public class ConcurrentReader<T> implements Runnable, Reader {

  /**
   * Poison pill to signal end of processing on student click files
   */
  public static final StudentClickInformation STUDENT_CLICK_POISON_PILL = new StudentClickInformation();

  /**
   * Poison pill to signal end of processing on course summary files
   */
  public static final CourseActivity COURSE_ACTIVITY_POISON_PILL = new CourseActivity();

  private String filePath;
  private Class beanClass;
  private BlockingQueue<T> objectQueue;
  private Iterator<T> iter;
  private int numConsumer;

  /**
   * Read the course file and create a Map with every course's information
   * @param courseMap a ConcurrentMap that has information of courses
   * @throws Exception an Exception when there is any problem reading file
   */
  public static void getCourses(ConcurrentMap<String, ConcurrentCourse> courseMap) throws Exception {
    BlockingQueue<ConcurrentCourse> courseQueue = new LinkedBlockingQueue<>();
    int numConsumer = 1;

    String courseFilePath = new File(ConcurrentMain.COURSE_FILE_NAME)
        .getAbsolutePath();
    Thread reader = new Thread(
        new ConcurrentReader(
            courseFilePath,
            courseQueue,
            ConcurrentCourse.class,
            numConsumer)
    );
    reader.start();
    try {
      reader.join();
    } catch (InterruptedException e) {
      throw new Exception(Reader.ERROR_READING_FILE);
    }

    while (!courseQueue.isEmpty()) {
      ConcurrentCourse course = courseQueue.poll();
      String courseInformation = String.format("%s_%s",
          course.getCodeModule(),
          course.getCodePresentation());
      courseMap.put(courseInformation, course);
    }
  }

  /**
   * Read generated summary files
   *
   * @return a List of File contains information of summary files
   * @throws Exception when there is an error reading files
   */
  public static List<File> getResultFiles() throws Exception {
    File summaryFolder = new File(ConcurrentWriter.OUTPUT_FOLDER_NAME);

    //Used to filter summary files only
    Pattern outputFilePattern = Pattern.compile(SUMMARY_FILE_PATTERN);
    FilenameFilter filter = (dir, name) -> outputFilePattern
        .matcher(new File(name)
            .getName())
        .matches();

    java.nio.file.DirectoryStream<java.nio.file.Path> dir = Files.newDirectoryStream(
        summaryFolder.toPath());
    Iterator<Path> paths = dir.iterator();
    List<File> fileList = new ArrayList<>();
    while (paths.hasNext()) {
      File file = paths.next().toFile();
      if (filter.accept(summaryFolder, file.getName())) {
        fileList.add(file);
      }
    }
    return fileList;
  }

  /**
   * Constructs a new Header object
   *
   * @param filePath    a String represents the file to read
   * @param objectQueue a BlockingQueue in order to add record
   * @param beanClass   a Class object that supports the serialization process
   * @param numConsumer an Integer represents number of consumer thread - for Poison Pill creation
   *                    purpose
   */
  public ConcurrentReader(String filePath, BlockingQueue<T> objectQueue, Class beanClass,
      int numConsumer) {
    this.filePath = filePath;
    this.objectQueue = objectQueue;
    this.beanClass = beanClass;
    this.numConsumer = numConsumer;
  }

  /**
   * Set up the serialization and iteration for file reading
   *
   * @param filePath  a String represents the file to read
   * @param beanClass a Class object that supports the serialization process
   * @throws Exception
   */
  private synchronized void setUpParser(String filePath, Class beanClass) throws Exception {
    File file = new File(filePath);
    Path path = Path.of(file.toURI());
    try (
        java.io.Reader reader = Files.newBufferedReader(path);
    ) {
      CsvToBean<T> parser = new CsvToBeanBuilder(reader)
          .withType(beanClass)
          .withSkipLines(NUM_LINE_TO_SKIP)
          .withIgnoreLeadingWhiteSpace(true)
          .build();

      this.iter = parser.iterator();
      while (iter.hasNext()) {
        T obj = iter.next();

        //Additional task to do for CourseActivity - part 3
        if (beanClass == CourseActivity.class) {
          CourseActivity activity = (CourseActivity) obj;
          activity.setCourseInfo(file
              .getName()
              .replace(".csv", ""));
        }
        objectQueue.put(obj);
      }

      //Poison Pill to signal end of file
      for (int i = 0; i < numConsumer; i++) {
        if (beanClass.equals(StudentClickInformation.class)) {
          objectQueue.put((T) STUDENT_CLICK_POISON_PILL);
        } else if (beanClass.equals(CourseActivity.class)) {
          objectQueue.put((T) COURSE_ACTIVITY_POISON_PILL);
        }
      }

    } catch (Exception e) {
      throw new Exception(ERROR_READING_FILE);
    }
  }

  /**
   * Not used for concurrent solution
   *
   * @return a generics object parsed from csv file
   */
  @Override
  public T nextLine() {
    return null;
  }

  /**
   * Start running a thread
   */
  @Override
  public void run() {
    try {
      System.out.println(String.format(Reader.READING_MESSAGE, filePath));
      setUpParser(filePath, beanClass);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * @param o another ConcurrentReader object
   * @return true if the two objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConcurrentReader<?> that = (ConcurrentReader<?>) o;
    return numConsumer == that.numConsumer && Objects.equals(filePath, that.filePath)
        && Objects.equals(beanClass, that.beanClass) && Objects.equals(
        objectQueue, that.objectQueue);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(filePath, beanClass, objectQueue, numConsumer);
  }
}
