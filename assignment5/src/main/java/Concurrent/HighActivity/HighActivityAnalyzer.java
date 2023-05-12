package Concurrent.HighActivity;

import Concurrent.ConcurrentReader;
import Concurrent.ConcurrentWriter;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Represents an analyzer that finds courses and dates that have total number of clicks larger than
 * the threshold
 *
 * @author Cody Cao, Letian Shi
 */
public class HighActivityAnalyzer implements Runnable {

  /**
   * Output file name of the high activity courses
   */
  public static final String HIGH_ACTIVITY_OUTPUT_FILE = "activity-%d";
  /**
   * Number of analyzer thread running at the same time
   */
  public static final int NUM_ANALYZER = 1;
  private long threshold;
  private Set<CourseActivity> resultSet;
  private BlockingQueue<CourseActivity> courseActivityBlockingQueue;
  private int numFiles;

  /**
   * @param threshold  a Long represents the threshold for courses with high activity dates
   * @param numThreads an Integer represents number of thread available
   * @throws Exception when there is an error reading/writing files
   */
  public static void getHighActivityCourses(long threshold, int numThreads) throws Exception {
    int numReaders = numThreads - NUM_ANALYZER;
    BlockingQueue<CourseActivity> courseActivityBlockingQueue = new LinkedBlockingQueue<>(
        numReaders);

    Set<CourseActivity> resultSet = Collections.synchronizedSet(new HashSet<>());
    List<File> fileList = ConcurrentReader.getResultFiles();

    //Set up threads
    Thread analyzerThread = new Thread(
        new HighActivityAnalyzer(threshold,
            courseActivityBlockingQueue,
            resultSet,
            fileList.size()));
    analyzerThread.start();

    ExecutorService executor = Executors.newFixedThreadPool(numReaders);
    for (File file : fileList) {
      Thread thread = new Thread(
          new ConcurrentReader<CourseActivity>(
              file.getAbsolutePath(),
              courseActivityBlockingQueue,
              CourseActivity.class,
              NUM_ANALYZER
          ));

      executor.submit(thread);
    }

    //Shut down threads
    executor.shutdown();
    executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    analyzerThread.join();

    //Write to file
    ConcurrentWriter.writeHighActivityFile(threshold, resultSet);
  }

  /**
   * Constructs a new HighActivityAnalyzer object
   *
   * @param threshold                   a long represents a threshold that determines whether a date
   *                                    has a high activity
   * @param courseActivityBlockingQueue a BlockingQueue that is given to the thread in order to
   *                                    handle information that the readers give
   * @param resultSet                   a synchronizedSet represents the list of courses' dates that
   *                                    has number of click passed the threshold
   * @param numFiles                    an integer represents number of files in the directory -
   *                                    used to determine when all file has been read. Compared with
   *                                    number of poison pills received from the readers.
   */
  public HighActivityAnalyzer(long threshold,
      BlockingQueue<CourseActivity> courseActivityBlockingQueue,
      Set<CourseActivity> resultSet,
      int numFiles) {
    this.threshold = threshold;
    this.courseActivityBlockingQueue = courseActivityBlockingQueue;
    this.resultSet = resultSet;
    this.numFiles = numFiles;
  }

  /**
   * Analyzes the course's click information
   *
   * @param activity a CourseActivity object used to update the list of high activity courses
   */
  public synchronized void analyze(CourseActivity activity) {
    if (activity.getNumClicks() < threshold) {
      return;
    }
    resultSet.add(activity);
  }

  /**
   * Starts an analyzer thread
   */
  @Override
  public void run() {
    try {
      int finishedFiles = 0;
      while (true) {
        CourseActivity activity = courseActivityBlockingQueue.take();
        if (activity == ConcurrentReader.COURSE_ACTIVITY_POISON_PILL) {
          finishedFiles++;
          if (finishedFiles == numFiles) {
            return;
          }
        } else {
          analyze(activity);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @param o another HighActivityAnalyzer object
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
    HighActivityAnalyzer that = (HighActivityAnalyzer) o;
    return threshold == that.threshold && numFiles == that.numFiles && Objects.equals(
        resultSet, that.resultSet) && Objects.equals(courseActivityBlockingQueue,
        that.courseActivityBlockingQueue);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(threshold, resultSet, courseActivityBlockingQueue, numFiles);
  }
}
