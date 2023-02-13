package Problem1;

import java.util.Objects;

/**
 * Class Runner contains information about a runner, including runner's name, their height, weight,
 * league, best 5K time, best haf marathon time, and their favorite running event.
 */
public class Runner extends Athlete {

  private double best5KTime, bestHalfMarathonTime;
  private String favoriteRunningEvent;

  /**
   * Construct a new Runner object.
   *
   * @param athletesName         object Name, containing runner's first, middle and last name
   * @param height               runner's height, expressed as a Double in cm (e.g., 6'2'' is
   *                             recorded as 187.96cm)
   * @param weight               runner's weigh, expressed as a Double in pounds (e.g. 125, 155, 200
   *                             pounds)
   * @param league               runner's league, expressed as String
   * @param best5KTime           runner's best 5K time, expressed as double
   * @param bestHalfMarathonTime runner's best half-marathon time, expressed as double
   * @param favoriteRunningEvent runner's favorite running event, expressed as String
   */
  public Runner(Name athletesName, Double height, Double weight, String league, double best5KTime,
      double bestHalfMarathonTime, String favoriteRunningEvent) {
    super(athletesName, height, weight, league);
    this.best5KTime = best5KTime;
    this.bestHalfMarathonTime = bestHalfMarathonTime;
    this.favoriteRunningEvent = favoriteRunningEvent;
  }

  /**
   * Construct a new Runner object.
   *
   * @param athletesName         object Name, containing runner's first, middle and last name
   * @param height               runner's height, expressed as a Double in cm (e.g., 6'2'' is
   *                             recorded as 187.96cm)
   * @param weight               runner's weigh, expressed as a Double in pounds (e.g. 125, 155, 200
   *                             pounds)
   * @param best5KTime           runner's best 5K time, expressed as double
   * @param bestHalfMarathonTime runner's best half-marathon time, expressed as double
   * @param favoriteRunningEvent runner's favorite running event, expressed as String
   */
  public Runner(Name athletesName, Double height, Double weight, double best5KTime,
      double bestHalfMarathonTime, String favoriteRunningEvent) {
    super(athletesName, height, weight);
    this.best5KTime = best5KTime;
    this.bestHalfMarathonTime = bestHalfMarathonTime;
    this.favoriteRunningEvent = favoriteRunningEvent;
  }

  /**
   * @return runner's best 5K time, expressed as double
   */
  public double getBest5KTime() {
    return best5KTime;
  }

  /**
   * @return runner's best half-marathon time, expressed as double
   */
  public double getBestHalfMarathonTime() {
    return bestHalfMarathonTime;
  }

  /**
   * @return runner's favorite running event, expressed as String
   */
  public String getFavoriteRunningEvent() {
    return favoriteRunningEvent;
  }

  /**
   * @param o another Runner object
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
    if (!super.equals(o)) {
      return false;
    }
    Runner runner = (Runner) o;
    return Double.compare(runner.best5KTime, best5KTime) == 0
        && Double.compare(runner.bestHalfMarathonTime, bestHalfMarathonTime) == 0
        && favoriteRunningEvent.equals(runner.favoriteRunningEvent);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), best5KTime, bestHalfMarathonTime, favoriteRunningEvent);
  }

  /**
   * @return a String with the object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Runner{");
    sb.append("best5KTime=").append(best5KTime);
    sb.append(", bestHalfMarathonTime=").append(bestHalfMarathonTime);
    sb.append(", favoriteRunningEvent='").append(favoriteRunningEvent).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
