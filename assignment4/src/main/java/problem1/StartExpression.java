package problem1;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Generate terminal result for users
 *
 * @author Cody Cao, Letian Shi
 */
public class StartExpression implements Expression {

  private List<String> template;
  private String result;
  private String temp[];
  private final String space = " ";
  private final int zero = 0;
  private NonTerminalExpression tempStore;
  private Random r;

  /**
   * constructor
   *
   * @param template grammar template
   */
  public StartExpression(List<String> template) {
    this.template = template;
  }

  /**
   * constructor only for tests
   * @param template grammar template
   * @param ranSeed random seed
   */
  public StartExpression(List<String> template,int ranSeed) {
    this.template = template;
    this.r=new Random(ranSeed);
  }
  /**
   * initialize result with blank
   */
  private void resetResult() {
    result = "";
  }

  /**
   * firstPos is the first position of the substring, which is 1
   *
   * @param allData allData is all data from JSON files
   * @return terminal result that will represent to users
   * @throws Exception when there is an error constructing the output
   */
  @Override
  public String generateOutput(ExpressionDirectory allData) throws Exception {
    resetResult();
    tempStore = new NonTerminalExpression("temp", template);
    for (int i = 0; i < template.size(); i++) {
      result = result + tempStore.generateOutput(allData, i) + space;
    }
    return result.substring(zero, result.length() - firstPos);
  }
  /**
   * only for test
   *
   * @param allData allData is all data from JSON files
   * @param ranSeed random seed
   * @return terminal result that will represent to users
   * @throws Exception when there is an error constructing the output
   */
  public String generateOutput(ExpressionDirectory allData,int ranSeed) throws Exception {
    resetResult();
    tempStore = new NonTerminalExpression("temp", template,ranSeed);
    for (int i = 0; i < template.size(); i++) {
      result = result + tempStore.generateOutput(allData, i) + space;
    }
    return result.substring(zero, result.length() - firstPos);
  }

  /**
   * @param o another StartExpression object
   * @return true if the two objects are equal structurally
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartExpression that = (StartExpression) o;
    return Objects.equals(template, that.template) && Objects.equals(result,
        that.result);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(template, result);
  }
}
