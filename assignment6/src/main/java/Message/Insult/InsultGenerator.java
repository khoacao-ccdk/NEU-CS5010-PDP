package Message.Insult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents an insult generator - used to generate a random insult message to another client
 *
 * @author Cody Cao, Letian Shi
 */
public class InsultGenerator {

  /**
   * a String represents the insult txt file
   */
  public static final String INSULT_FILE_NAME = "InsultText.txt";

  /**
   * an error thrown when the insult file could not be read
   */
  public static final String ERROR_READING_FILE = "There was an error reading insult file";

  private List<String> insults;
  Random rand;

  /**
   * Constructs a new InsultGenerator
   *
   * @throws Exception when there is an exception reading from txt file
   */
  public InsultGenerator() throws Exception {
    rand = new Random();
    readFromFile();
  }

  /**
   * Constructs a new InsultGenerator - for testing purpose
   *
   * @param seed an Integer represents the seed for random number generator
   * @throws Exception when there is an exception reading from txt file
   */
  public InsultGenerator(long seed) throws Exception {
    rand = new Random(seed);
    readFromFile();
  }

  /**
   * Generates a random insult sentence that is sent to another person
   *
   * @return a String represents the insult that is sent to another user
   */
  public String getInsult() {
    int pos = rand.nextInt(insults.size());
    return insults.get(pos);
  }

  /**
   * Get the insults from the txt file in resource folder
   */
  private void readFromFile() throws Exception {
    this.insults = new ArrayList<>();
    try {
      File insultFile = new File(INSULT_FILE_NAME);
      BufferedReader reader = new BufferedReader(new FileReader(insultFile));
      String line;
      while ((line = reader.readLine()) != null) {
        insults.add(line);
      }
      reader.close();
    } catch (Exception e) {
      throw new Exception(ERROR_READING_FILE);
    }
  }

  /**
   * @param o another InsultGenerator object
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
    InsultGenerator that = (InsultGenerator) o;
    return Objects.equals(insults, that.insults);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(insults, rand);
  }

  /**
   * @return a String with the object's information
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("InsultGenerator{");
    sb.append("insults=").append(insults);
    sb.append('}');
    return sb.toString();
  }
}
