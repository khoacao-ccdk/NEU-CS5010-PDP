/**
 * TemplateHandler Interface.
 *
 * @author Cody Cao
 */
import Customer.*;
public interface TemplateHandler {

  /**
   * Retrieve the message template from a file name
   *
   * @param fileName a String represents the template's file name
   * @throws Exception an Exception when the template cannot be retrieved
   */
  void getTemplate(String fileName) throws Exception;

  /**
   * Generates a message for a customer using the given template
   *
   * @param c         a Customer object
   * @param outputDir a String represents the output directory
   * @throws Exception an Exception when the message cannot be created
   */
  void generateMessage(Customer c, String outputDir) throws Exception;
}
