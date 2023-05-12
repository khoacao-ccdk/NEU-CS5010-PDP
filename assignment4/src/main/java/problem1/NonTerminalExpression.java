package problem1;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Store data in a map format and generate sub-result that will be used to generate terminal results
 * for users
 *
 * @author Cody Cao, Letian Shi
 */
public class NonTerminalExpression implements Expression {

  private List<String> data;
  private String key;
  private String result;
  private int randomIndex;
  private String targetStr;
  private Random r;
  private String mid[];

  /**
   * @param key  a String represents the NonTerminalExpression's key
   * @param data a List represents the NonTerminalExpression's data
   */
  public NonTerminalExpression(String key, List<String> data) {
    this.data = data;
    this.key = key;
    this.r = new Random();
  }

  /**
   * @param key     a String represents the NonTerminalExpression's key
   * @param data    a List represents the NonTerminalExpression's data
   * @param ranSeed an Integer represents a seed for random method - for testing purpose only
   */
  public NonTerminalExpression(String key, List<String> data, int ranSeed) {
    this.data = data;
    this.key = key;
    r = new Random(ranSeed);
  }

  /**
   * update result for one time, firstPos is the first position of the substring, which is 1
   *
   * @param allData all data from JSON files
   * @param mid     String array contains brackets and need to be replaced by non-terminal words
   * @throws NullPointerException check if selected non-terminal exists
   */
  private void replaceSub(ExpressionDirectory allData, String[] mid) throws Exception {
    for (int q = 0; q < mid.length; q++) {
      if (mid[q].matches(indicator)) {
        targetStr = mid[q].substring(firstPos, mid[q].length() - 1);
        if (allData.getSubData(targetStr) == null) {
          throw new NullPointerException("Non terminal not found.");
        }
        randomIndex = r.nextInt(allData.getSubData(targetStr).data.size());
        mid[q] = allData.getSubData(targetStr).data.get(randomIndex);
      }
    }
    result = String.join(" ", mid);
  }

  /**
   * used to generate random index for current NonTerminalExpression words list
   */
  private int generateIndex() {
    randomIndex = r.nextInt(this.data.size());
    return randomIndex;
  }

  /**
   * @return string to be used in the terminal expression
   */
  private String select() {
    return this.data.get(generateIndex());
  }

  /**
   * replace all bracket indicator with correct words
   *
   * @param allData all data from JSON files
   * @return string with replaced words
   */
  private String replace(ExpressionDirectory allData, String str) throws Exception {
    result = str;
    while (result.matches(indicator)) {
      this.mid = result.split(" ");
      replaceSub(allData, this.mid);
    }

    return result;
  }

  /**
   * @param allData allData is all data from JSON files
   * @return terminal result that will represent to users
   * @throws Exception when there is an error constructing the output
   */
  @Override
  public String generateOutput(ExpressionDirectory allData) throws Exception {
    result = "";
    replace(allData, select());
    return result;
  }

  /**
   * used to generate results with specified index
   *
   * @param allData allData is all data from JSON files
   * @param index   the index of the data that we want to replace brackets
   * @return terminal result that will represent to users
   * @throws Exception when there is an error constructing the output
   */
  public String generateOutput(ExpressionDirectory allData, int index) throws Exception {
    result = "";
    replace(allData, this.data.get(index));
    return result;
  }

  /**
   * @param o another NonTerminalExpression object
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
    NonTerminalExpression that = (NonTerminalExpression) o;
    return Objects.equals(data, that.data) && key.equalsIgnoreCase(that.key)
        && Objects.equals(result, that.result);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(data, key, result);
  }
}
