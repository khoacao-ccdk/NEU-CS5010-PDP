package Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a customer directory. This class is used to retrieve customers' information from the
 * csv file
 *
 * @author Cody Cao
 */
public class CustomerDirectory {

  /**
   * Error message when the csv file cannot be found
   */
  public static final String ERROR_CSV_NOT_FOUND = "Could not find the specified csv file";

  /**
   * Integer value indicates that the information is not in the csv file
   */
  private static final int NOT_IN_CSV = -1;

  private List<Customer> customerList;

  private int firstNameIndex,
      lastNameIndex,
      companyNameIndex,
      addressIndex,
      cityIndex,
      countyIndex,
      stateIndex,
      zipIndex,
      phone1Index,
      phone2Index,
      emailIndex,
      webIndex;

  /**
   * Constructs a new CustomerDirectory object. This function will give an error message when the
   * specified csv file cannot be found
   *
   * @param fileName a String represents the csv filename to retrieve customers' information
   * @throws Exception an Exception message when the csv file could not be found
   */
  public CustomerDirectory(String fileName) throws Exception {
    this.customerList = new ArrayList<>();
    BufferedReader reader = null;
    StringBuilder sb = new StringBuilder();
    try {
      String f = getClass().getResource(fileName).getPath();
      reader = new BufferedReader(new FileReader(f));
      //Retrieve header indexes
      String header = reader.readLine();
      if (header != null) {
        getHeaderIndex(header);
      }

      String line = new String();
      while ((line = reader.readLine()) != null) {
        //Split line into a String array
        String[] splited = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < splited.length; i++) {
          splited[i] = splited[i].replaceAll("\"", "");
        }
        if (splited.length < 12) {
          continue;
        }

        //Parse the split string array into Customer object
        Name customerName = new Name(
            firstNameIndex != NOT_IN_CSV ? splited[firstNameIndex] : "",
            lastNameIndex != NOT_IN_CSV ? splited[lastNameIndex] : ""
        );
        String companyName = companyNameIndex != NOT_IN_CSV ? splited[companyNameIndex] : "";
        Address address = new Address(
            addressIndex != NOT_IN_CSV ? splited[addressIndex] : "",
            cityIndex != NOT_IN_CSV ? splited[cityIndex] : "",
            countyIndex != NOT_IN_CSV ? splited[countyIndex] : "",
            stateIndex != NOT_IN_CSV ? splited[stateIndex] : "",
            zipIndex != NOT_IN_CSV ? splited[zipIndex] : ""
        );
        ContactInformation contact = new ContactInformation(
            phone1Index != NOT_IN_CSV ? splited[phone1Index] : "",
            phone2Index != NOT_IN_CSV ? splited[phone2Index] : "",
            emailIndex != NOT_IN_CSV ? splited[emailIndex] : "",
            webIndex != NOT_IN_CSV ? splited[webIndex] : ""
        );
        Customer c = new Customer(customerName, companyName, address, contact);
        customerList.add(c);
      }
      reader.close();
    } catch (Exception e) {
      throw new Exception(ERROR_CSV_NOT_FOUND);
    }
  }


  /**
   * Get index of headers in the csv file
   *
   * @param h a String represents the headers in the csv file
   */
  private void getHeaderIndex(String h) {
    firstNameIndex =
        lastNameIndex =
            companyNameIndex =
                addressIndex =
                    cityIndex =
                        countyIndex =
                            stateIndex =
                                zipIndex =
                                    phone1Index =
                                        phone2Index =
                                            emailIndex =
                                                webIndex = NOT_IN_CSV;

    String[] headers = h.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    for (int i = 0; i < headers.length; i++) {
      headers[i] = headers[i]
          .trim()
          .replaceAll("\"", "");
      switch (headers[i]) {
        case "first_name":
          firstNameIndex = i;
          break;
        case "last_name":
          lastNameIndex = i;
          break;
        case "company_name":
          companyNameIndex = i;
          break;
        case "address":
          addressIndex = i;
          break;
        case "city":
          cityIndex = i;
          break;
        case "county":
          countyIndex = i;
          break;
        case "state":
          stateIndex = i;
          break;
        case "zip":
          zipIndex = i;
          break;
        case "phone1":
          phone1Index = i;
          break;
        case "phone2":
          phone2Index = i;
          break;
        case "email":
          emailIndex = i;
          break;
        case "web":
          webIndex = i;
          break;
      }
    }
  }

  /**
   * @return a List represents information of all customer in the csv file
   */
  public List<Customer> getCustomerList() {
    return this.customerList;
  }

  /**
   * @param o another Customer directory object
   * @return true if the two objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerDirectory that = (CustomerDirectory) o;
    return Objects.equals(customerList, that.customerList);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(customerList);
  }
}
