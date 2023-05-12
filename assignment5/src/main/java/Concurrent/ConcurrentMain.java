package Concurrent;

import Concurrent.HighActivity.HighActivityAnalyzer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Main Class for part 2-3
 *
 * @author Cody Cao, Letian Shi
 */
public class ConcurrentMain {

  /**
   * Default threshold value - used when the threshold is not provided in Command Line arguments
   */
  public static final long THRESHOLD_DEFAULT_VALUE = Long.MAX_VALUE;

  /**
   * Courses file name to give to reader
   */
  public static final String COURSE_FILE_NAME = "courses.csv";

  /**
   * Student click information file names to give to reader
   */
  public static final String STUDENT_CLICK_FILE_NAME = "studentVle.csv";

  /**
   * Main Processing function
   *
   * @param args for part 3: A threshold to determine whether a day is considered high activity day
   */
  public static void main(String[] args) {
    long threshold = THRESHOLD_DEFAULT_VALUE;
    if (args.length != 0) {
      threshold = Long.parseLong(args[0]);
    }

    int numThreads = Runtime.getRuntime().availableProcessors();
    ConcurrentMap<String, ConcurrentCourse> courseMap = new ConcurrentHashMap<>();
    try {
      ConcurrentReader.getCourses(courseMap);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }

    //Start Analyzing
    ConcurrentAnalyzer.analyze(numThreads, courseMap);

    //Write to output summary files
    try {
      ConcurrentWriter.writeSummaryFile(numThreads, courseMap);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    //Analyze high activity courses
    if(threshold != Long.MAX_VALUE){
      try {
        HighActivityAnalyzer.getHighActivityCourses(threshold, numThreads);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }


}
