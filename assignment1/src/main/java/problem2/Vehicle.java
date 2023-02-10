package problem2;

/**
 * Represents a Vehicle
 *
 * @author Cody Cao
 */
public abstract class Vehicle {

  private String id;
  private int year;
  private MakeModel makeModel;
  private double msrp;

  /**
   * Any class that extends Vehicle will be using this constructor. Construct a new Vehicle object
   * given the vehicle's id, year, make and model, msrp.
   *
   * @param id        a String represents the vehicle's id
   * @param year      an int represent the vehicle's manufacturing year
   * @param makeModel a MakeModel object that holds information about the vehicle's make and model
   * @param msrp      a double represents the vehicle's Manufacturer Suggested Retail Price
   */
  protected Vehicle(String id, int year, MakeModel makeModel, double msrp) {
    this.id = id;
    this.year = year;
    this.makeModel = makeModel;
    this.msrp = msrp;
  }

  /**
   * @return vehicle's unique Id
   */
  public String getId() {
    return id;
  }

  /**
   * @return vehicle's manufacturing year
   */
  public int getYear() {
    return year;
  }

  /**
   * @param year set vehicle's manufacturing year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * @return a MakeModel object that contains information about the vehicle's make and model
   */
  public MakeModel getMakeModel() {
    return makeModel;
  }

  /**
   * Set make and model for information for a vehicle
   *
   * @param makeModel a MakeModel object that contains information about the vehicle's make and
   *                  model
   */
  public void setMakeModel(MakeModel makeModel) {
    this.makeModel = makeModel;
  }

  /**
   * @return vehicle's Manufacturer Suggested Retail Price
   */
  public double getMsrp() {
    return msrp;
  }

  /**
   * Set vehicle's Manufacturer Suggested Retail Price
   *
   * @param msrp vehicle's Manufacturer Suggested Retail Price
   */
  public void setMsrp(double msrp) {
    this.msrp = msrp;
  }
}
