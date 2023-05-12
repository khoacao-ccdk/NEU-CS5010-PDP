package Common;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Objects;

/**
 * StudentClickInformation bean class, represents a student's interaction online
 *
 * @author Cody Cao, Letian Shi
 */
public class StudentClickInformation {

  @CsvBindByPosition(position = 0)
  private String codeModule;

  @CsvBindByPosition(position = 1)
  private String codePresentation;

  @CsvBindByPosition(position = 4)
  private int date;

  @CsvBindByPosition(position = 5)
  private int numClicks;

  /**
   * Constructs a new StudentClickInformation - java bean convention
   */
  public StudentClickInformation() {
  }

  /**
   * Constructs a new StudentClickInformation object - for testing purpose
   *
   * @param codeModule       a String represents the code module
   * @param codePresentation a String represents the code presentation
   * @param date             an Integer represents a date
   * @param numClicks        an Integer represents number of click
   */
  public StudentClickInformation(String codeModule, String codePresentation, int date,
      int numClicks) {
    this.codeModule = codeModule;
    this.codePresentation = codePresentation;
    this.date = date;
    this.numClicks = numClicks;
  }

  /**
   * @return a String represents the code module
   */
  public String getCodeModule() {
    return codeModule;
  }

  /**
   * Set the code module
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
   * Set the code's presentation
   *
   * @param codePresentation a String represents the code presentation
   */
  public void setCodePresentation(String codePresentation) {
    this.codePresentation = codePresentation;
  }

  /**
   * @return an Integer represents a date
   */
  public int getDate() {
    return date;
  }

  /**
   * Set the date of the record
   *
   * @param date an Integer represents a date
   */
  public void setDate(int date) {
    this.date = date;
  }

  /**
   * @return an Integer represents number of click
   */
  public int getNumClicks() {
    return numClicks;
  }

  /**
   * Set the number of clicks
   *
   * @param numClicks an Integer represents number of click
   */
  public void setNumClicks(int numClicks) {
    this.numClicks = numClicks;
  }

  /**
   * @param o another StudentClickInformation object
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
    StudentClickInformation that = (StudentClickInformation) o;
    return date == that.date && numClicks == that.numClicks && Objects.equals(codeModule,
        that.codeModule) && Objects.equals(codePresentation, that.codePresentation);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(codeModule, codePresentation, date, numClicks);
  }

  /**
   * @return a String with the object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("StudentClickInformation{");
    sb.append("codeModule='").append(codeModule).append('\'');
    sb.append(", codePresentation='").append(codePresentation).append('\'');
    sb.append(", date=").append(date);
    sb.append(", numClicks=").append(numClicks);
    sb.append('}');
    return sb.toString();
  }
}
