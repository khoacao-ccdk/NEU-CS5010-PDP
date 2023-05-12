package problem1;


import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Reppresents an ExpressionDirectory object that stores all non-terminal expressions
 *
 * @author Cody Cao, Letian Shi
 */
public class ExpressionDirectory {

  /**
   * ERROR message thrown when the non-terminal expression is in a sentence but not in the JSON
   * file
   */
  public static final String ERROR_TERMINAL_NOT_FOUND = "Terminal %s not found in JSON file";

  private Map<String, NonTerminalExpression> allData;

  /**
   * Construct a new ExpressionDirectory object
   */
  public ExpressionDirectory() {
    allData = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  }

  /**
   *
   * @return a Map with all data in the directory
   */
  public Map<String, NonTerminalExpression> getAllData() {
    return allData;
  }

  /**
   * Add a new Expression to the directory
   *
   * @param key  a String represents the non-terminal expression's key
   * @param data a NonTerminalExpression object that holds information of a non-terminal expression
   */
  public void put(String key, NonTerminalExpression data) {
    allData.put(key, data);
  }

  /**
   * @param key a String represents the non-terminal expression's key
   * @return a NonTerminalExpression object that holds information of a non-terminal expression
   * @throws Exception if there is no NonTerminalExpression object found for the specified key
   */
  public NonTerminalExpression getSubData(String key) throws Exception {
    if (allData.containsKey(key)) {
      return allData.get(key);
    } else {
      throw new Exception(String.format(ERROR_TERMINAL_NOT_FOUND, key));
    }
  }

  /**
   * @param o another ExpressionDirectory object
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
    ExpressionDirectory directory = (ExpressionDirectory) o;
    return Objects.equals(allData, directory.allData);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(allData);
  }
}
