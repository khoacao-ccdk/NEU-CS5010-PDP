package Concurrent;

import Common.Analyzer;
import Common.StudentClickInformation;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * ConcurrentAnalyzer - Main Processing class
 *
 * @author Cody cao, Letian Shi
 */
public class ConcurrentAnalyzer implements Runnable, Analyzer {

  private BlockingQueue<StudentClickInformation> clickQueue;
  private ConcurrentMap<String, ConcurrentCourse> courseMap;

  /**
   * Analyze number of clicks per student interaction record and update the course's total number of
   * click according to date
   *
   * @param numThreads an Integer represents number of thread available to the function
   * @param courseMap  a ConcurrentMap that has information of every course
   */
  public static void analyze(
      int numThreads,
      ConcurrentMap<String, ConcurrentCourse> courseMap
  ) {
    int numConsumer = numThreads - 1;
    BlockingQueue<StudentClickInformation> clickQueue = new ArrayBlockingQueue<>(numConsumer);
    String clickFilePath = new File(ConcurrentMain.STUDENT_CLICK_FILE_NAME)
        .getAbsolutePath();

    //Set up threads
    Thread reader = new Thread(
        new ConcurrentReader<StudentClickInformation>(
            clickFilePath,
            clickQueue,
            StudentClickInformation.class,
            numConsumer)
    );
    reader.start();

    Thread[] threads = new Thread[numThreads - 1];
    for (int i = 0; i < numThreads - 1; i++) {
      threads[i] = new Thread(
          new ConcurrentAnalyzer(
              clickQueue,
              courseMap
          ));
      threads[i].start();
    }

    //Shut down threads
    try {
      reader.join();
      for (Thread thread : threads) {
        thread.join();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructs a new analyzer object
   *
   * @param clickQueue a BlockingQueue that has student's click information
   * @param courseMap  a Map that has all the courses
   */
  public ConcurrentAnalyzer(BlockingQueue<StudentClickInformation> clickQueue,
      ConcurrentMap<String, ConcurrentCourse> courseMap) {
    this.clickQueue = clickQueue;
    this.courseMap = courseMap;
  }

  /**
   * Update total number of click for a course
   *
   * @param click a StudentClickInformation object that has information about a student's
   *              interaction with a course
   */
  @Override
  public synchronized void updateCourse(StudentClickInformation click) {
    if (click == null) {
      return;
    }

    //Get Information from click object
    String courseInformation = String.format("%s_%s",
        click.getCodeModule(),
        click.getCodePresentation());
    int date = click.getDate();
    int numClick = click.getNumClicks();

    //Update total click of a course
    ConcurrentCourse course = courseMap.get(courseInformation);
    ConcurrentMap<Integer, Long> clickRecord = course.getClickRecord();
    clickRecord.merge(date, (long) numClick, Long::sum);
  }

  /**
   * Starts an analyzer thread
   */
  @Override
  public void run() {
    StudentClickInformation click;
    while (true) {
      try {
        click = clickQueue.take();
        if (click == ConcurrentReader.STUDENT_CLICK_POISON_PILL) {
          return;
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      updateCourse(click);
    }
  }

  /**
   * @param o another ConcurrentAnalyzer object
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
    ConcurrentAnalyzer that = (ConcurrentAnalyzer) o;
    return Objects.equals(clickQueue, that.clickQueue) && Objects.equals(
        courseMap, that.courseMap);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(clickQueue, courseMap);
  }
}
