package Sequential;

import static Sequential.AnalyzeHelper.createClickSum;
import static Sequential.AnalyzeHelper.createCourseData;
import static Sequential.AnalyzeHelper.createOuput;

import Common.StudentClickInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * SequentialAnalyzer - Main Processing class
 *
 * @author Cody cao, Letian Shi
 */
public class SequentialAnalyzer {

  private static String directory;
  private static SequentialWriter SW;
  private static SequentialReader courseSR;
  private static SequentialReader studentSR;
  private static ArrayList<String> courseList;
  private static Map<String, Map<String, Integer>> clickMap;
  private static final String clicks = "studentVle.csv";
  private static final String courses = "courses.csv";
  private static String coursePath;
  private static String clickPath;

  /**
   *
   * @param args input arguments
   * @throws Exception if file not exist
   */
  public static void main(String args[]) throws Exception {
    if (args.length <1) {
      directory = "";
    }
    if (coursePath == null && clickPath == null) {
      setInputPath(courses, clicks);
    }
    courseSR = new SequentialReader<SequentialCourse>(coursePath, SequentialCourse.class);
    studentSR = new SequentialReader<StudentClickInformation>(clickPath,
        StudentClickInformation.class);
    SW = new SequentialWriter(directory, null, null);
    SW.createFile(directory);
    courseSR.setUpParser();
    studentSR.setUpParser();
    courseList = new ArrayList<>();
    clickMap = new HashMap<>();
    courseList = createCourseData(courseSR, courseList);
    clickMap = createClickSum(studentSR, clickMap, courseList);
    createOuput(clickMap, directory);
  }

  /**
   * Only used for test
   *
   * @param coursePath course input file path
   * @param clickPath click input file path
   */
  public static void setInputPath(String coursePath, String clickPath) {
    SequentialAnalyzer.clickPath = clickPath;
    SequentialAnalyzer.coursePath = coursePath;
  }

  /**
   *
   * @return coursePath
   */
  public static String getCoursePath() {
    return coursePath;
  }

  /**
   *
   * @return clickPath
   */
  public static String getClickPath() {
    return clickPath;
  }
}
