package Sequential;

import Common.Course;
import java.util.Map;
/**
 * SequentialCourse is a realization of abstract class Course
 *
 * @author Cody cao, Letian Shi
 */
public class SequentialCourse extends Course {

  /**
   * @return a Map represents a course's click record on a daily basis
   */
  @Override
  public Map<Integer, Long> getClickRecord() {
    return null;
  }

  /**
   * @param o another Course object
   * @return true if the two objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * @return a String represents the course's information
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
