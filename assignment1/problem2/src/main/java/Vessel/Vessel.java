/**
 * Represents a Vessel. One subclass: Boat
 *
 * @author Cody Cao
 */
public abstract class Vessel extends Vehicle {

  /**
   * Any class that extends Vessel will be using this constructor. Construct a new Vessel object
   * given the vessel's id, year, make and model, msrp.
   *
   * @param id        a String represents the vessel's id
   * @param year      an int represent the vessel's manufacturing year
   * @param makeModel a MakeModel object that holds information about the vessel's make and model
   * @param msrp      a double represents the vessel's Manufacturer Suggested Retail Price
   */
  protected Vessel(String id, int year, MakeModel makeModel, double msrp) {
    super(id, year, makeModel, msrp);
  }
}
