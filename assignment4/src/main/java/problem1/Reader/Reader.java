package problem1.Reader;

import java.util.List;
import problem1.Grammar;

/**
 * Reader Interface - defines a set of methods for any class that are used to read a directory of
 * grammar files
 */
public interface Reader {

  /**
   * Grammar's start part - key in Json file
   */
  String JSON_HEADER_START = "start";

  /**
   * Grammar's title - key in Json file
   */
  String JSON_HEADER_TITLE = "grammarTitle";

  /**
   * Grammar's description - key in Json file
   */
  String JSON_HEADER_DESCRIPTION = "grammarDesc";

  /**
   * Error message when reading the directory that holds JSON files
   */
  String ERROR_COULD_NOT_READ_DIRECTORY = "Error. Could not find the directory name specified";


  /**
   * Return a list of available grammars in the directory. If the grammar list is already available,
   * simply returns the list
   *
   * @param directoryName a String represents the directory's name
   * @return a List of Grammar objects represents the available grammars in the directory
   * @throws Exception when there is an error reading directory
   */
  List<Grammar> getGrammarList(String directoryName) throws Exception;

  /**
   * Populate the Grammar object that is chosen by the user with the information.
   *
   * @param grammar a Grammar object represents the grammar that is chosen by the user to generate
   *                sentence
   * @throws Exception when there is an error reading the JSON file of the grammar
   */
  void getGrammarBody(Grammar grammar) throws Exception;
}
