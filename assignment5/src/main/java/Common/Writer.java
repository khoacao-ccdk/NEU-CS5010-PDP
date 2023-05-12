package Common;

/**
 * Writer Interface
 *
 * @author Cody Cao, Letian Shi
 */
public interface Writer {

  /**
   * Default output file format
   */
  String DEFAULT_OUTPUT_FILE_FORMAT = "%s.csv";

  /**
   * Error message when there is an error creating output file
   */
  String ERROR_CANNOT_CREATE = "There was an error creating output file(s)";

  /**
   * Error message when there is an error creating output file
   */
  String ERROR_CANNOT_WRITE = "There was an error writing to output file(s)";

  /**
   * Headers for the summary files
   */
  String[] COURSE_SUMMARY_HEADER = new String[]{"date", "total_clicks"};

  /**
   * Headers for the high activity file output
   */
  String[] HIGH_ACTIVITY_HEADER = new String[]{"course_info", "date", "total_click"};

  /**
   * Create a new file
   * @param filePath a String represents the path to file
   * @throws Exception when there is an exception creating file
   */
  void createFile(String filePath) throws Exception;

  /**
   * Write data to a file
   * @param fileName a String represents the path to file
   * @param data an Object represents the data needed to write to file
   * @param append a Boolean telling writer to either append or overwrite
   * @throws Exception when there is an Exception writing to file
   */
  void writeToFile(String fileName, String[] data, boolean append) throws Exception;
}
