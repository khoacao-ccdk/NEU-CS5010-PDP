package Concurrent;

import Common.Course;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Represents a ConcurrentCourse object
 *
 * @author Cody Cao, Letian Shi
 */
public class ConcurrentCourse extends Course {

  /**
   * Constructs a new ConcurrentCourse object
   */
  public ConcurrentCourse() {
    super();
    clickRecord = new ConcurrentHashMap<>();
  }

  /**
   * Construct a new ConcurrentCourse object - for testing only
   *
   * @param codeModule       a String represents the course module
   * @param codePresentation a String represents the course presentation
   * @param clickRecord      a Map with the course's click record summary
   */
  public ConcurrentCourse(String codeModule, String codePresentation,
      ConcurrentMap<Integer, Long> clickRecord) {
    super(codeModule, codePresentation, clickRecord);
  }

  /**
   * @return a ConcurrentMap represents the course's click record on a daily basis
   */
  @Override
  public ConcurrentMap<Integer, Long> getClickRecord() {
    return (ConcurrentMap<Integer, Long>) clickRecord;
  }
}
