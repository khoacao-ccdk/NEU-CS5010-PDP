package Problem1;

import java.util.Objects;

/**
 * Class BaseballPlayer contains information about a baseball player, including baseballplayer's
 * name, their height, weight, league, team, average batting, season home runs .
 */
public class BaseballPlayer extends Athlete {

  private String team;
  private double batting;
  private int numHomeRun;

  /**
   * Construct a new baseballplayer object.
   *
   * @param athletesName object Name, containing baseballplayer's first, middle and last name
   * @param height       baseballplayer's height, expressed as a Double in cm (e.g., 6'2'' is
   *                     recorded as 187.96cm)
   * @param weight       baseballplayer's weigh, expressed as a Double in pounds (e.g. 125, 155, 200
   *                     pounds)
   * @param league       baseballplayer's league, expressed as String
   * @param team         baseball player's team, expressed as String
   * @param batting      baseball player's average batting, expressed as Double
   * @param numHomeRun   baseball player's season home runs, expressed as Integer
   */
  public BaseballPlayer(Name athletesName, Double height, Double weight, String league, String team,
      double batting, int numHomeRun) {
    super(athletesName, height, weight, league);
    this.team = team;
    this.batting = batting;
    this.numHomeRun = numHomeRun;
  }

  /**
   * Construct a new baseballplayer object.
   *
   * @param athletesName object Name, containing baseballplayer's first, middle and last name
   * @param height       baseballplayer's height, expressed as a Double in cm (e.g., 6'2'' is
   *                     recorded as 187.96cm)
   * @param weight       baseballplayer's weigh, expressed as a Double in pounds (e.g. 125, 155, 200
   *                     pounds)
   * @param team         baseball player's team, expressed as String
   * @param batting      baseball player's team, expressed as String
   * @param numHomeRun   baseball player's season home runs, expressed as Integer
   */
  public BaseballPlayer(Name athletesName, Double height, Double weight, String team,
      double batting,
      int numHomeRun) {
    super(athletesName, height, weight);
    this.team = team;
    this.batting = batting;
    this.numHomeRun = numHomeRun;
  }

  /**
   * @return baseball player's team, expressed as String
   */
  public String getTeam() {
    return team;
  }

  /**
   * @return baseball player's team, expressed as String
   */
  public double getBatting() {
    return batting;
  }

  /**
   * @return baseball player's season home runs, expressed as Integer
   */
  public int getNumHomeRun() {
    return numHomeRun;
  }

  /**
   * @param o another BaseballPlayer object
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
    BaseballPlayer that = (BaseballPlayer) o;
    return Double.compare(that.batting, batting) == 0 && numHomeRun == that.numHomeRun
        && team.equals(that.team);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), team, batting, numHomeRun);
  }

  /**
   * @return a String with object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("BaseballPlayer{");
    sb.append("team='").append(team).append('\'');
    sb.append(", batting=").append(batting);
    sb.append(", numHomeRun=").append(numHomeRun);
    sb.append('}');
    return sb.toString();
  }
}
