package Concurrent.HighActivity;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Objects;

/**
 * CourseActivity bean class, represents a number of clicks by date for a particular course
 *
 * @author Cody Cao, Letian Shi
 */
public class CourseActivity {
  private String courseInfo;

  @CsvBindByPosition(position = 0)
  private int date;

  @CsvBindByPosition(position = 1)
  private long numClicks;

  /**
   * Constructs a new CourseActivity object - for testing purpose only
   *
   * @param courseInfo a String represents the course's code and presentation
   * @param date       an int represents a date
   * @param numClicks  a Long represents a total number of clicks for that date
   */
  public CourseActivity(String courseInfo, int date, long numClicks) {
    this.courseInfo = courseInfo;
    this.date = date;
    this.numClicks = numClicks;
  }

  /**
   *
   * @return a String represents the course's code and presentation
   */
  public String getCourseInfo() {
    return courseInfo;
  }

  /**
   * Constructs a new CourseActivityObject - bean class conventions
   */
  public CourseActivity() {
  }

  /**
   * @return an Integer represents a date
   */
  public int getDate() {
    return date;
  }

  /**
   * @return a Long represents a total number of clicks for that date
   */
  public long getNumClicks() {
    return numClicks;
  }

  /**
   * Set the course's code and presentation
   * @param courseInfo a String represents the course's code and presentation
   */
  public void setCourseInfo(String courseInfo) {
    this.courseInfo = courseInfo;
  }

  /**
   * Set the date for a record
   *
   * @param date an Integer represents a date
   */
  public void setDate(int date) {
    this.date = date;
  }

  /**
   * Set total number of click for a record
   *
   * @param numClicks a Long represents a total number of clicks for that date
   */
  public void setNumClicks(long numClicks) {
    this.numClicks = numClicks;
  }

  /**
   * @param o another CourseActivity object
   * @return true if the two objects are equal structurally
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseActivity that = (CourseActivity) o;
    return date == that.date && numClicks == that.numClicks && Objects.equals(courseInfo,
        that.courseInfo);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(courseInfo, date, numClicks);
  }

  /**
   * @return a String with the object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("CourseActivity{");
    sb.append("courseInfo='").append(courseInfo).append('\'');
    sb.append(", date=").append(date);
    sb.append(", numClicks=").append(numClicks);
    sb.append('}');
    return sb.toString();
  }
}
