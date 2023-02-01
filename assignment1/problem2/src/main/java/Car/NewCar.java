/**
 * Represents a new car
 *
 * @author Cody Cao
 */
public class NewCar extends Car {

  private int numAvailable;

  /**
   * Create a new NewCar object given the car's id, year, make and model, msrp, and number of
   * available vehicle within 50 miles
   *
   * @param id           a String represents the car's id
   * @param year         an int represent the car's manufacturing year
   * @param makeModel    a MakeModel object that holds information about the car's make and model
   * @param msrp         a double represents the car's Manufacturer Suggested Retail Price
   * @param numAvailable an int represents the number of vehicle available within 50 miles
   */
  public NewCar(String id, int year, MakeModel makeModel, double msrp, int numAvailable) {
    super(id, year, makeModel, msrp);
    this.numAvailable = numAvailable;
  }

  /**
   * @return a number of available vehicles within 50 miles
   */
  public int getNumAvailable() {
    return numAvailable;
  }

  /**
   * @param numAvailable number of available vehicles within 50 miles
   */
  public void setNumAvailable(int numAvailable) {
    this.numAvailable = numAvailable;
  }
}
