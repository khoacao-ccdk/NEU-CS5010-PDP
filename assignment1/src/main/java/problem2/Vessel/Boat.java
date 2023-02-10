package problem2.Vessel;

import problem2.MakeModel;

/**
 * Represents a Boat object
 *
 * @author Cody Cao
 */
public class Boat extends Vessel {

  private float length;
  private int numPassenger;
  private PropulsionType propType;

  /**
   * Create a new Boat object given the boat's id, year, make and model, msrp, length, number of
   * passengers, and propulsion type
   *
   * @param id           a String represents the boat's id
   * @param year         an int represent the boat's manufacturing year
   * @param makeModel    a MakeModel object that holds information about the boat's make and model
   * @param msrp         a double represents the boat's Manufacturer Suggested Retail Price
   * @param numPassenger an int represents the boat's length
   * @param propType     a PropulsionType enumerator value represents the boat's propulsion type
   */
  public Boat(String id, int year, MakeModel makeModel, double msrp, float length, int numPassenger,
      PropulsionType propType) {
    super(id, year, makeModel, msrp);
    this.length = length;
    this.numPassenger = numPassenger;
    this.propType = propType;
  }

  /**
   * @return the boat's length
   */
  public float getLength() {
    return length;
  }

  /**
   * @param length the boat's length
   */
  public void setLength(float length) {
    this.length = length;
  }

  /**
   * @return number of passengers the boat can hold
   */
  public int getNumPassenger() {
    return numPassenger;
  }

  /**
   * @param numPassenger number of passengers the boat can hold
   */
  public void setNumPassenger(int numPassenger) {
    this.numPassenger = numPassenger;
  }

  /**
   * @return an enumerator value represents the boat's propulsion type including Sail Power, Inboard
   * Engine,Outboard Engine, Jet Propulsion.
   */
  public PropulsionType getPropType() {
    return propType;
  }

  /**
   * @param propType an enumerator value represents the boat's propulsion type, including Sail
   *                 Power, Inboard Engine, Outboard Engine, Jet Propulsion.
   */
  public void setPropType(PropulsionType propType) {
    this.propType = propType;
  }
}
