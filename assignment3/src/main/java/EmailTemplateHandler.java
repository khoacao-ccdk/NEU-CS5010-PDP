import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Represents the Email template handler
 *
 * @author Cody Cao
 */
public class EmailTemplateHandler implements TemplateHandler {

  /**
   * Format of the output email file name
   */
  public static final String OUTPUT_FILE_NAME = "%s_%s_email.txt";

  /**
   * Template file not found message
   */
  public static final String ERROR_TEMPLATE_NOT_FOUND = "Cannot find a template file with specified file name";

  /**
   * Could not create emails message
   */
  public static final String ERROR_CANNOT_CREATE = "Could not create emails";
  private String template;

  /**
   * Constructs a new EmailTemplateHandler object
   */
  public EmailTemplateHandler() {
    this.template = null;
  }

  /**
   * Retrieve the email template from a file name
   *
   * @param fileName a String represents the template's file name
   * @throws Exception an Exception when the template cannot be retrieved
   */
  @Override
  public void getTemplate(String fileName) throws Exception {
    BufferedReader reader = null;
    StringBuilder sb = new StringBuilder();
    try {
      String f = getClass().getResource(fileName).getPath();
      reader = new BufferedReader(new FileReader(f));
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line).append("\n");
      }
      reader.close();
    } catch (Exception e) {
      throw new Exception(ERROR_TEMPLATE_NOT_FOUND);
    }
    this.template = sb.toString();
  }

  /**
   * Generates an email for a customer
   *
   * @param c         a Customer object
   * @param outputDir a String represents the output directory
   * @throws Exception an Exception when the message cannot be created
   */
  @Override
  public void generateMessage(Customer c, String outputDir) throws Exception {
    String res = this.template;

    //Populate required information in the template
    res = res
        .replaceAll("\\[[^]]first_name\\]\\]", c.getName().getFirstName())
        .replaceAll("\\[[^]]last_name\\]\\]", c.getName().getLastName())
        .replaceAll("\\[[^]]company_name\\]\\]", c.getCompany())
        .replaceAll("\\[[^]]address\\]\\]", c.getAddress().getAddress())
        .replaceAll("\\[[^]]city\\]\\]", c.getAddress().getCity())
        .replaceAll("\\[[^]]county\\]\\]", c.getAddress().getCounty())
        .replaceAll("\\[[^]]state\\]\\]", c.getAddress().getState())
        .replaceAll("\\[[^]]zip\\]\\]", c.getAddress().getZip())
        .replaceAll("\\[[^]]email\\]\\]", c.getContact().getEmail())
        .replaceAll("\\[[^]]phone1\\]\\]", c.getContact().getPhone1())
        .replaceAll("\\[[^]]phone2\\]\\]", c.getContact().getPhone2())
        .replaceAll("\\[[^]]web\\]\\]", c.getContact().getWeb());

    try {
      String outputFileName = String.format(OUTPUT_FILE_NAME,
          c.getName().getFirstName(),
          c.getName().getLastName()
      );
      //Create a new folder with the given output directory name
      Files.createDirectories(Paths.get(outputDir));
      File dir = new File(outputDir);

      //Create a new file and write the letter body to it
      File outputFile = new File(dir, outputFileName);
      Writer output = new BufferedWriter(new FileWriter(outputFile));
      output.write(res);
      output.close();
    } catch (Exception e) {
      throw new Exception(ERROR_CANNOT_CREATE);
    }
  }

  /**
   * @param o another EmailTemplateHandler object
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
    EmailTemplateHandler that = (EmailTemplateHandler) o;
    return Objects.equals(template, that.template);
  }

  /**
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(template);
  }
}
