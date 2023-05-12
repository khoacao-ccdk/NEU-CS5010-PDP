package problem1;

import java.util.Objects;

/**
 * Represents a grammar object
 *
 * @author Cody Cao, Letian Shi
 */
public class Grammar {

  private Expression startExpression;
  private ExpressionDirectory expressionDirectory;
  private String grammarTitle;
  private String grammarDescription;
  private String filePath;

  /**
   * Constructs a new Grammar object
   *
   * @param grammarTitle          a String represents the title of the grammar
   * @param getGrammarDescription a String represents the description of the grammar
   * @param filePath              a String with the file path to the grammar's JSON file for further
   *                              lookup
   */
  public Grammar(String grammarTitle, String getGrammarDescription, String filePath) {
    this.grammarTitle = grammarTitle;
    this.grammarDescription = getGrammarDescription;
    this.filePath = filePath;
  }

  /**
   * @return a String represents the starting/main structure of the grammar
   */
  public Expression getStartExpression() {
    return startExpression;
  }

  /**
   * @return an ExpressionDirectory object containing grammar's non-terminal expressions
   */
  public ExpressionDirectory getExpressionDirectory() {
    return expressionDirectory;
  }

  /**
   * @return a String represents the title of the grammar
   */
  public String getGrammarTitle() {
    return grammarTitle;
  }

  /**
   * @return a String represents the description of the grammar
   */
  public String getGrammarDescription() {
    return grammarDescription;
  }

  /**
   * @return a String with the file path to the grammar's JSON file for further lookup
   */
  public String getFilePath() {
    return filePath;
  }

  /**
   * Set the starting/main structure of the grammar
   *
   * @param startExpression a String represents the starting/main structure of the grammar
   */
  public void setStartExpression(Expression startExpression) {
    this.startExpression = startExpression;
  }

  /**
   * Set the grammar's non-terminal expressions directory
   *
   * @param expressionDirectory an ExpressionDirectory object containing grammar's non-terminal
   *                            expressions
   */
  public void setExpressionDirectory(ExpressionDirectory expressionDirectory) {
    this.expressionDirectory = expressionDirectory;
  }

  /**
   * Generates an output
   * @return a String represents the output of the grammar
   * @throws Exception when the sentence could not be generated
   */
  public String generateOutput() throws Exception {
    return startExpression.generateOutput(expressionDirectory);
  }

  /**
   * @param o another Grammar object
   * @return true if the two Grammar objects are equal structurally, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grammar grammar = (Grammar) o;
    return Objects.equals(startExpression, grammar.startExpression)
        && Objects.equals(expressionDirectory, grammar.expressionDirectory)
        && Objects.equals(grammarTitle, grammar.grammarTitle) && Objects.equals(
        grammarDescription, grammar.grammarDescription);
  }

  /**
   * @return an Integer represents the grammar's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(startExpression, expressionDirectory, grammarTitle, grammarDescription);
  }

  /**
   * @return a String with information about the grammar object
   */
  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Grammar{");
    sb.append("startExpression=").append(startExpression);
    sb.append(", expressionDirectory=").append(expressionDirectory);
    sb.append(", grammarTitle='").append(grammarTitle).append('\'');
    sb.append(", grammarDescription='").append(grammarDescription).append('\'');
    sb.append(", filePath='").append(filePath).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
