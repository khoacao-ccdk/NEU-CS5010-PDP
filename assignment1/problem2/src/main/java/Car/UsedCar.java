/**
 * Represents a used car
 *
 * @author Cody Cao
 */
public class UsedCar extends Car {

  private int mileage;
  private int numOwners;
  private int numAccidents;


  /**
   * Create a new UsedCar object given the car's id, year, make and model, msrp, number of mileage,
   * number of previous owners, and number of minor accidents the car has been involved in
   *
   * @param id           a String represents the car's id
   * @param year         an int represent the car's manufacturing year
   * @param makeModel    a MakeModel object that holds information about the car's make and model
   * @param msrp         a double represents the car's Manufacturer Suggested Retail Price
   * @param mileage      an int represents the number of mileage currently on the car
   * @param numOwners    an int represents number of previous owner
   * @param numAccidents an int represents number of minor accidents the car has been involved in
   */
  public UsedCar(String id, int year, MakeModel makeModel, double msrp, int mileage, int numOwners,
      int numAccidents) {
    super(id, year, makeModel, msrp);
    this.mileage = mileage;
    this.numOwners = numOwners;
    this.numAccidents = numAccidents;
  }

  /**
   * @return number of mileage currently on the car
   */
  public int getMileage() {
    return mileage;
  }

  /**
   * @param mileage number of mileage currently on the car
   */
  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  /**
   * @return number of previous owners
   */
  public int getNumOwners() {
    return numOwners;
  }

  /**
   * @param numOwners number of previous owners
   */
  public void setNumOwners(int numOwners) {
    this.numOwners = numOwners;
  }

  /**
   * @return number of minor traffic accidents the vehicle has been involved in
   */
  public int getNumAccidents() {
    return numAccidents;
  }

  /**
   * @param numAccidents number of minor traffic accidents the vehicle has been involved in
   */
  public void setNumAccidents(int numAccidents) {
    this.numAccidents = numAccidents;
  }
}
