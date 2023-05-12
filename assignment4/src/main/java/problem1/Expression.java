package problem1;

/**
 * Expression Interface, represents grammar's start expression and non-terminal expression
 */
public interface Expression {

  /**
   * Regex to get non-terminal expression keys from string
   */
  String indicator = ".*\\<(\\w*)\\>*.*";
  /**
   * First position to be used in substring
   */
  int firstPos = 1;

  /**
   * @param allData allData is all data from JSON files
   * @return terminal result that will represent to users
   * @throws Exception when there is an error constructing the output
   */
  String generateOutput(ExpressionDirectory allData) throws Exception;
}
