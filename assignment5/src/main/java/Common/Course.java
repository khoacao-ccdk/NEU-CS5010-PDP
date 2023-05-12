package Common;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Map;
import java.util.Objects;

/**
 * abstract class represents a Course - java bean
 *
 * @author Cody Cao, Letian Shi
 */
public abstract class Course {

  @CsvBindByPosition(position = 0)
  private String codeModule;

  @CsvBindByPosition(position = 1)
  private String codePresentation;
  /**
   * a Map used to store the click record for the course
   */
  protected Map<Integer, Long> clickRecord;

  /**
   * Constructs a new Course - java bean convention
   */
  public Course() {
  }

  /**
   * Constructs a new StudentClickInformation object - for testing purpose
   *
   * @param codeModule       a String represents the code module
   * @param codePresentation a String represents the code presentation
   * @param clickRecord      a Map storing a key-value pair of click per day for a course
   */
  public Course(String codeModule, String codePresentation, Map<Integer, Long> clickRecord) {
    this.codeModule = codeModule;
    this.codePresentation = codePresentation;
    this.clickRecord = clickRecord;
  }

  /**
   * @return a String represents the code module
   */
  public String getCodeModule() {
    return codeModule;
  }

  /**
   * Set the String represents the code module
   *
   * @param codeModule a String represents the code module
   */
  public void setCodeModule(String codeModule) {
    this.codeModule = codeModule;
  }

  /**
   * @return a String represents the code presentation
   */
  public String getCodePresentation() {
    return codePresentation;
  }

  /**
   * Set the code presentation
   *
   * @param codePresentation a String represents the code presentation
   */
  public void setCodePresentation(String codePresentation) {
    this.codePresentation = codePresentation;
  }

  /**
   * @return a Map represents a course's click record on a daily basis
   */
  abstract public Map<Integer, Long> getClickRecord();

  /**
   * @param o another Course object
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
    Course course = (Course) o;
    return Objects.equals(codeModule, course.codeModule) && Objects.equals(
        codePresentation, course.codePresentation) && Objects.equals(clickRecord,
        course.clickRecord);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(codeModule, codePresentation, clickRecord);
  }

  /**
   * @return a String represents the course's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Course{");
    sb.append("codeModule='").append(codeModule).append('\'');
    sb.append(", codePresentation='").append(codePresentation).append('\'');
    sb.append(", clickRecord=").append(clickRecord);
    sb.append('}');
    return sb.toString();
  }
}
