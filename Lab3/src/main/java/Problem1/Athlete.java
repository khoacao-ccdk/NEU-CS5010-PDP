package Problem1;

import java.util.Objects;

/*
 * Class Athlete contains information about an athlete, including athlete's name, their height, weight and league.
 */
public class Athlete {

  private Name athletesName;
  private Double height;
  private Double weight;
  private String league;

  /*
   * Constructs a new athlete, based upon all of the provided input parameters.
   * @param athletesName - object Name, containing athlete's first, middle and last name
   * @param height - athlete's height, expressed as a Double in cm (e.g., 6'2'' is recorded as 187.96cm)
   * @param weight - athlete's weigh, expressed as a Double in pounds (e.g. 125, 155, 200 pounds)
   * @param league - athelete's league, expressed as String
   * @return - object Athlete
   */
  public Athlete(Name athletesName, Double height, Double weight, String league) {
    this.athletesName = athletesName;
    this.height = height;
    this.weight = weight;
    this.league = league;
  }
  /*
   * Constructs a new athlete, based upon all of the provided input parameters.
   * @param athletesName - object Name, containing athlete's first, middle and last name
   * @param height - athlete's height, expressed as a Double in cm (e.g., 6'2'' is recorded as 187.96cm)
   * @param weight - athlete's weigh, expressed as a Double in pounds (e.g. 125, 155, 200 pounds)
   * @return - object Athlete, with league field set to null
   */

  public Athlete(Name athletesName, Double height, Double weight) {
    this.athletesName = athletesName;
    this.height = height;
    this.weight = weight;
    this.league = null;
  }

  /*
   * Returns athlete's name as an object Name
   */
  public Name getAthletesName() {
    return athletesName;
  }

  /*
   * Returns athlete's height as a Double
   */
  public Double getHeight() {
    return height;
  }

  /*
   * Returns athlete's weight as a Double
   */
  public Double getWeight() {
    return weight;
  }

  /*
   * Returns athlete's league as a String
   */
  public String getLeague() {
    return league;
  }

  /**
   * @param o another Athlete object
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
    Athlete athlete = (Athlete) o;
    return athletesName.equals(athlete.athletesName) && height.equals(athlete.height)
        && weight.equals(athlete.weight) && league.equals(athlete.league);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(athletesName, height, weight, league);
  }

  /**
   * @return a String contains object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Athlete{");
    sb.append("athletesName=").append(athletesName);
    sb.append(", height=").append(height);
    sb.append(", weight=").append(weight);
    sb.append(", league='").append(league).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
