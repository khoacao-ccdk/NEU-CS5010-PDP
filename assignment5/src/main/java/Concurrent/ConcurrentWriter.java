package Concurrent;

import Common.Course;
import Common.Writer;
import Concurrent.HighActivity.CourseActivity;
import Concurrent.HighActivity.HighActivityAnalyzer;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A Writer class that can be run in parallel
 *
 * @author Cody Cao, Letian Shi
 */
public class ConcurrentWriter<T> implements Runnable, Writer {

  /**
   * Name of the output folder of the analysis
   */
  public static final String OUTPUT_FOLDER_NAME = "ConcurrentResult";
  private String filePath;
  private Class beanClass;
  private T data;
  private File outputFile;

  /**
   * Write to course summary files
   *
   * @param numThreads an Integer represents number of threads
   * @param courseMap  a Map that holds summarized course information
   * @throws Exception when there is an error writing to file
   */
  public static void writeSummaryFile(
    int numThreads,
    ConcurrentMap<String, ConcurrentCourse> courseMap) throws Exception {
    //Create output files (overwriting any file already in the folder)
    for (String name : courseMap.keySet()) {
      Writer writer = new ConcurrentWriter<Course>(name);
      writer.writeToFile(name, Writer.COURSE_SUMMARY_HEADER, false);
    }

    //Fill data of output files
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    Iterator<String> iter = courseMap.keySet().iterator();
    while (iter.hasNext()) {
      String key = iter.next();
      ConcurrentCourse course = courseMap.getOrDefault(key, null);
      Thread writer = new Thread(new ConcurrentWriter(key, course, Course.class));
      executor.submit(writer);
    }
    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Write to file that contains courses higher than activity
   *
   * @param threshold a long determine high activity threshold
   * @param resultSet a Set contains list of activity that has dates with number of clicks higher
   *                  than threshold
   * @throws Exception when there is an error creating file
   */
  public static void writeHighActivityFile(long threshold, Set<CourseActivity> resultSet)
      throws Exception {
    //Create output files (overwriting any file already in the folder)
    String fileName = String.format(HighActivityAnalyzer.HIGH_ACTIVITY_OUTPUT_FILE, threshold);
    Writer writer = new ConcurrentWriter<Course>(fileName);
    try {
      writer.writeToFile(fileName, Writer.HIGH_ACTIVITY_HEADER, false);
    } catch (Exception e) {
      throw new Exception(ERROR_CANNOT_WRITE);
    }

    Thread executor = new Thread(new ConcurrentWriter<>(fileName, resultSet, CourseActivity.class));
    executor.start();
    executor.join();
  }

  /**
   * Constructs a new ConcurrentWriter class - used when wanting to write data to a file
   *
   * @param filePath  a String represents the path to file
   * @param data      an Object that has the data of a file
   * @param beanClass a Class object telling Writer which class to handle
   */
  public ConcurrentWriter(String filePath, T data, Class beanClass) {
    this.filePath = filePath;
    this.data = data;
    this.beanClass = beanClass;
  }

  /**
   * Constructs a new ConcurrentWriter class - used when wanting to create a new file
   *
   * @param filePath a String represents the path to file
   */
  public ConcurrentWriter(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Creates a new file
   *
   * @param filePath a String represents the path to file
   */
  @Override
  public void createFile(String filePath) throws Exception {
    //Verify if there already is a folder. If not, create the folder
    try {
      //Create a new folder with the given output directory name
      Files.createDirectories(Paths.get(OUTPUT_FOLDER_NAME));

      File outputFile = new File(OUTPUT_FOLDER_NAME,
          String.format(DEFAULT_OUTPUT_FILE_FORMAT, filePath));
      outputFile.createNewFile();

      this.outputFile = outputFile;
    } catch (Exception e) {
      throw new Exception(ERROR_CANNOT_CREATE);
    }
  }

  /**
   * Write data to file
   *
   * @param filePath a String represents the path to file
   * @param data     a String array represents the data needed to write to file
   * @param append   a Boolean telling writer to either append or overwrite
   */
  @Override
  public void writeToFile(String filePath, String[] data, boolean append) throws Exception {
    try {
      if (outputFile == null) {
        createFile(filePath);
      }

      java.io.Writer writer = new BufferedWriter(
          new FileWriter(
              this.outputFile, append
          ));

      CSVWriter output = new CSVWriter(writer,
          CSVWriter.DEFAULT_SEPARATOR,
          CSVWriter.NO_QUOTE_CHARACTER,
          CSVWriter.DEFAULT_ESCAPE_CHARACTER,
          CSVWriter.DEFAULT_LINE_END);

      output.writeNext(data);
      output.close();
    } catch (Exception e) {
      throw new Exception(ERROR_CANNOT_WRITE);
    }
  }

  /**
   * Write a course's summary data to a file
   *
   * @throws Exception an Exception thrown when there's an error writing to file
   */
  private void writeCourse() throws Exception {
    ConcurrentCourse course = (ConcurrentCourse) data;
    ConcurrentMap<Integer, Long> clickRecords = course.getClickRecord();

    for (Entry<Integer, Long> entry : clickRecords.entrySet()) {
      Integer date = entry.getKey();
      Long click = entry.getValue();
      writeToFile(this.filePath, new String[]{String.valueOf(date), String.valueOf(click)}, true);
    }
  }

  /**
   * Write information of courses and dates that pass the threshold value
   *
   * @throws Exception an Exception thrown when there's an error writing to file
   */
  private void writeHighActivity() throws Exception {
    Set<CourseActivity> outputData = (Set) data;
    for (CourseActivity activity : outputData) {
      writeToFile(this.filePath, new String[]{
          activity.getCourseInfo(),
          String.valueOf(activity.getDate()),
          String.valueOf(activity.getNumClicks())
      }, true);
    }
  }

  /**
   * Start running the thread
   */
  @Override
  public void run() {
    try {
      if (data != null) {
        if (beanClass == Course.class) {
          writeCourse();
        } else {
          writeHighActivity();
        }
      } else {
        createFile(this.filePath);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param o another ConcurrentWriter object
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
    ConcurrentWriter<?> that = (ConcurrentWriter<?>) o;
    return Objects.equals(filePath, that.filePath) && Objects.equals(beanClass,
        that.beanClass) && Objects.equals(data, that.data);
  }

  /**
   * @return an Integer represents the object's hascode
   */
  @Override
  public int hashCode() {
    return Objects.hash(filePath, beanClass, data);
  }
}
