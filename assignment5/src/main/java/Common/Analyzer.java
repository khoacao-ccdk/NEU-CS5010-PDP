package Common;

/**
 * Analyzer interface
 *
 * @author Cody cao, Letian Shi
 */
public interface Analyzer {

  /**
   * Default number of click for a course on a given date
   */
  long DEFAULT_NUM_CLICK = 0;

  /**
   * Update total number of click for a course
   *
   * @param click a StudentClickInformation object that has information about a student's
   *              interaction with a course
   */
  void updateCourse(StudentClickInformation click);
}
