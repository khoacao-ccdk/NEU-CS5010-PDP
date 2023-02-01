/**
 * Represents a Car. Two subclasses: NewCar and UsedCar
 *
 * @author Cody Cao
 */
public abstract class Car extends Vehicle {

  /**
   * Any class that extends Car will be using this constructor. Construct a new Car object given the
   * car's id, year, make and model, msrp.
   *
   * @param id        a String represents the car's id
   * @param year      an int represent the car's manufacturing year
   * @param makeModel a MakeModel object that holds information about the car's make and model
   * @param msrp      a double represents the car's Manufacturer Suggested Retail Price
   */
  protected Car(String id, int year, MakeModel makeModel, double msrp) {
    super(id, year, makeModel, msrp);
  }
}
