package problem1.Reader;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import problem1.Expression;
import problem1.ExpressionDirectory;
import problem1.Grammar;
import problem1.NonTerminalExpression;
import problem1.StartExpression;

/**
 * Represents a JSON Reader object This class handles every file reading operations, including
 * reading from directory and read Grammar's data from JSON file
 *
 * @author Cody Cao, Letian Shi
 */
public class JSONReader implements Reader {

  /**
   * Error message when reading a JSON file
   */
  public static final String ERROR_COULD_NOT_READ_JSON = "There was an error reading JSON file.";
  private List<Grammar> grammarList;

  /**
   * Construct a new JSONReader object
   */
  public JSONReader() {
    grammarList = new ArrayList<>();
  }

  /**
   * Return a list of available grammars in the directory. If the grammar list is already available,
   * simply returns the list
   *
   * @param directoryName a String represents the directory's name
   * @return a List of Grammar objects represents the available grammars in the directory
   * @throws Exception when there is an error reading directory
   */
  public List<Grammar> getGrammarList(String directoryName) throws Exception {
    //If the list has previously been fetched, simply return the list
    if (grammarList.size() > 0) {
      return grammarList;
    }

    //If not, fetch the list of Grammar from the directory
    URL url;
    File grammarFolder;

    try {
      url = this.getClass().getClassLoader().getResource(directoryName);
      grammarFolder = new File(url.toURI());
    } catch (Exception e) {
      throw new Exception(ERROR_COULD_NOT_READ_DIRECTORY);
    }
    File[] fileList = grammarFolder.listFiles();

    JSONObject jObject;
    for (File file : fileList) {
      String path = file.getPath();
      jObject = getJSONObjectFromPath(path);

      String title = (String) jObject.get(JSON_HEADER_TITLE);
      String description = (String) jObject.get(JSON_HEADER_DESCRIPTION);

      Grammar grammar = new Grammar(title, description, path);
      grammarList.add(grammar);
    }

    return grammarList;
  }

  /**
   * Populate the Grammar object that is chosen by the user with the information.
   *
   * @param grammar a Grammar object represents the grammar that is chosen by the user to generate
   *                sentence
   * @throws Exception when there is an error reading the JSON file of the grammar
   */
  public void getGrammarBody(Grammar grammar) throws Exception {
    JSONObject jObject = getJSONObjectFromPath(grammar.getFilePath());

    ExpressionDirectory directory = new ExpressionDirectory();

    //Get grammar's start expression from Json file
    JSONArray startTemplateJSON = (JSONArray) jObject.get(JSON_HEADER_START);
    List<String> startTemplate = (List<String>)startTemplateJSON
                                .stream()
                                .map(Object::toString)
                                .toList();
    grammar.setStartExpression(new StartExpression(startTemplate));

    //Get grammar's non-terminal part from Json file
    Iterator iter = jObject.keySet().iterator();
    while (iter.hasNext()) {
      String key = (String) iter.next();
      if (jObject.get(key) instanceof JSONArray) {
        if (key.equals(JSON_HEADER_START) || key.equals(JSON_HEADER_TITLE) || key.equals(
            JSON_HEADER_DESCRIPTION)) {
          continue;
        }

        JSONArray values = (JSONArray) jObject.get(key);
        List<String> valueList = (List<String>)values
                                  .stream()
                                  .map(Object::toString)
                                  .toList();
        Expression exp = new NonTerminalExpression(key, valueList);
        directory.put(key, (NonTerminalExpression) exp);
      }
    }

    //Set Grammar's list of non-terminal expressions after getting needed information
    grammar.setExpressionDirectory(directory);
  }

  /**
   * @param path a String represents the path to fetch the JSON file
   * @return a JSONObject object represents the JSON file of the grammar that needs to be read
   * @throws Exception when there is an error reading the JSON file
   */
  private JSONObject getJSONObjectFromPath(String path) throws Exception {
    try {
      Object obj = new JSONParser().parse(new FileReader(path));
      JSONObject jObject = (JSONObject) obj;
      return jObject;
    } catch (Exception e) {
      throw new Exception(ERROR_COULD_NOT_READ_JSON);
    }
  }

  /**
   * @param o another JSONReader object
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
    JSONReader that = (JSONReader) o;
    return Objects.equals(grammarList, that.grammarList);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(grammarList);
  }
}
