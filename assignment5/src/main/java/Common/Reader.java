package Common;

/**
 * Reader Interface
 *
 * @author Cody Cao, Letian Shi
 */
public interface Reader <T> {
  /**
   * A String that gets printed when the reader is reading file
   */
  String READING_MESSAGE = "Reading %s";

  /**
   * Error message when there is a problem reading file
   */
  String ERROR_READING_FILE = "There was an error reading file";

  /**
   * For csv reader - set this will and the reader will skip the header line
   */
  int NUM_LINE_TO_SKIP = 1;

  /**
   * Pattern for reading summary file - Part 3
   */
  String SUMMARY_FILE_PATTERN = "[A-Za-z0-9]+_[A-Za-z0-9]+\\.csv";

  /**
   * @return an Object that represents the next line's information
   * @throws Exception when there's an exception reading file
   */
  T nextLine() throws Exception;
}
