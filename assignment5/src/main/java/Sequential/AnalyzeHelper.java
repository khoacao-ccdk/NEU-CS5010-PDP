package Sequential;

import Common.StudentClickInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * AnalyzeHelper is a class to hold implementation helper methods for main method
 *
 * @author Cody cao, Letian Shi
 */
public class AnalyzeHelper {

  private static final int SIZE_OF_COURSE = 2;

  /**
   * @param date   date of the clicks
   * @param clicks number of clicks
   * @return one row data and click data
   */
  public static String[] createOneRowRecord(String date, String clicks) {
    String[] result = new String[2];
    result[0] = date;
    result[1] = clicks;
    return result;
  }

  /**
   * @param courseSR   sequential reader for course.csv
   * @param courseList course data info list
   * @return course data info list
   */
  public static ArrayList createCourseData(SequentialReader<SequentialCourse> courseSR,
      ArrayList courseList) {
    SequentialCourse course;
    while (courseSR.getIter().hasNext()) {
      course = (SequentialCourse) courseSR.nextLine();
      courseList.add(course.getCodeModule() + "_" + course.getCodePresentation());
    }
    return courseList;
  }

  /**
   *
   * @param clickMap map of map that holds all info data
   * @param directory output directory
   * @throws Exception if file not exist
   */
  public static void createOuput(Map<String, Map<String, Integer>> clickMap, String directory)
      throws Exception {
    for (Map.Entry<String, Map<String, Integer>> allData : clickMap.entrySet()) {
      if (allData.getValue() != null) {
        for (Entry<String, Integer> pair : allData.getValue().entrySet()) {
          SequentialWriter SW = new SequentialWriter(directory, allData.getKey(),
              createOneRowRecord(pair.getKey(),
                  String.valueOf(pair.getValue())));
          SW.writeToFile(SW.getFilename(), SW.getData(), true);
        }
      }
    }
  }

  /**
   *
   * @param studentSR sequential reader for click data
   * @param clickMap map of map that holds all info data
   * @param courseList course info list
   * @return clickMap, map of map that holds all info data
   */
  public static Map<String, Map<String, Integer>> createClickSum(
      SequentialReader<StudentClickInformation> studentSR,
      Map<String, Map<String, Integer>> clickMap, ArrayList<String> courseList) {
    //clickData is used to temporarily store one row of data
    StudentClickInformation clickData;
    //key is the combination of course_module and code_presentation from clickData
    String key;

    for (int i = 0; i < courseList.size(); i++) {
      clickMap.put(courseList.get(i), null);
    }
    while (studentSR.getIter().hasNext()) {
      clickData = (StudentClickInformation) studentSR.nextLine();
      key = clickData.getCodeModule() + "_" + clickData.getCodePresentation();
      Map<String, Integer> numClick = new HashMap<>();
      if (clickMap.get(key) == null) {
        numClick.put(String.valueOf(clickData.getDate()), clickData.getNumClicks());
        clickMap.put(key, numClick);
      } else if (clickMap.get(key).containsKey(String.valueOf(clickData.getDate()))) {
        clickMap.get(key).put(String.valueOf(clickData.getDate()),
            clickData.getNumClicks() + clickMap.get(key).get(String.valueOf(clickData.getDate())));
      } else {
        clickMap.get(key).put(String.valueOf(clickData.getDate()), clickData.getNumClicks());
      }
    }
    return clickMap;
  }
}
