package problem2;

/**
 * Represents information about a vehicle's make and model
 *
 * @author Cody Cao
 */
public class MakeModel {

  private String make;
  private String model;

  public MakeModel(String make, String model) {
    this.make = make;
    this.model = model;
  }

  /**
   * @return a vehicle's maker
   */
  public String getMake() {
    return make;
  }

  /**
   * Set a vehicle's maker
   *
   * @param make a vehicle's maker
   */
  public void setMake(String make) {
    this.make = make;
  }

  /**
   * @return a vehicle's model
   */
  public String getModel() {
    return model;
  }

  /**
   * @param model a vehicle's model
   */
  public void setModel(String model) {
    this.model = model;
  }
}
