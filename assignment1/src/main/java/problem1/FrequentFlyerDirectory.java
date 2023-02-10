package problem1;

import java.util.HashMap;

/**
 * Represent a Flyer Directory that holds information of every FrequentFlyer
 *
 * @author Cody Cao
 */
public class FrequentFlyerDirectory {

  private HashMap<String, FrequentFlyer> flyerList;

  /**
   * Create a new FrequentFlyerDirectory object that holds information of frequent flyers
   */
  public FrequentFlyerDirectory() {
    this.flyerList = new HashMap<>();
  }

  /**
   * @param flyer: Information of a new FrequentFlyer to add to Flyer Directory
   */
  public final void addNewFlyer(FrequentFlyer flyer) {
    if(flyer == null)
      return;
    flyerList.put(flyer.getId(), flyer);
    flyer.setDirectory(this);
  }

  /**
   * Verifies that the given id and name matches a frequent flyer's account in the application
   * @param id:   A String represents Flyer's id
   * @param name:
   * @return FrequentFlyer object of the receiver; null if the provided information does not match
   * any account
   */
  protected FrequentFlyer verifyFlyerInfo(String id, Name name) {
    //Check if there is an account with the given id
    if (!this.flyerList.containsKey(id)) {
      return null;
    }
    //Check if the account's name matches what is given
    FrequentFlyer flyer = this.flyerList.get(id);
    if (!name.equals(flyer.getName())) {
      return null;
    }
    //If previous steps do not raise an error, return true
    return flyer;
  }
}
