import java.util.Objects;

/**
 * Represents the user command handling part of the program
 *
 * @author Cody Cao
 */
public class CommandHandler {

  /**
   * A String provides user with currently available commands
   */
  public static final String USAGE = new StringBuilder("Usage:").append("\n")
      .append("\t--email only generate email messages").append("\n")
      .append(
          "\t--email-template <file> accept a filename that holds the email template. Required if --email is used")
      .append("\n")
      .append("\t--letter only generate letters").append("\n")
      .append(
          "\t--letter-template <file> accept a filename that holds the letter template. Required if --letter is used")
      .append("\n")
      .append(
          "\t--output-dir <path> accept the name of a folder, all output is placed in this folder")
      .append("\n")
      .append("\t--csv-file <path> accept the name of the csv file to process").toString();

  /**
   * a String to match email argument
   */
  public static final String USAGE_EMAIL = "email";

  /**
   * a String to match email template argument
   */
  public static final String USAGE_EMAIL_TEMPLATE = "email-template";

  /**
   * a String to match letter argument
   */
  public static final String USAGE_LETTER = "letter";

  /**
   * a String to match letter template argument
   */
  public static final String USAGE_LETTER_TEMPLATE = "letter-template";

  /**
   * a String to match output argument
   */
  public static final String USAGE_OUTPUT = "output-dir";

  /**
   * a String to match csv file argument
   */
  public static final String USAGE_CSV = "csv-file";

  /**
   * Error message when an unknown command was detected
   */
  public static final String ERROR_UNKNOWN_COMMAND = "Error: Unknown command";

  /**
   * Error message when letter template argument was missing
   */
  public static final String ERROR_NO_LETTER_TEMPLATE = "Error: --letter provided but no --letter-template was given";

  /**
   * Error message when the letter template file name was not specified
   */
  public static final String ERROR_NO_LETTER_TEMPLATE_DIR = "Error: letter template file name was not specified";

  /**
   * Error message when email template argument was missing
   */
  public static final String ERROR_NO_EMAIL_TEMPLATE = "Error: --email provided but no --email-template was given";

  /**
   * Error message when email template file name was not specified
   */
  public static final String ERROR_NO_EMAIL_TEMPLATE_DIR = "Error: email template file name was not given";

  /**
   * Error message when output directory argument was missing
   */
  public static final String ERROR_NO_OUTPUT = "Error: --output-dir was not given";

  /**
   * Error message when output directory name was not specified
   */
  public static final String ERROR_NO_OUTPUT_DIR = "Error: Output directory was not specified";

  /**
   * Error message when csv argument was missing
   */
  public static final String ERROR_NO_CSV = "Error: --csv-file was not given";

  /**
   * Error message when csv file name was not specified
   */
  public static final String ERROR_NO_CSV_DIR = "Error: csv file name was not specified";

  private TemplateHandler templateHandler;
  private CustomerDirectory directory;

  private boolean email,
      email_template,
      letter,
      letter_template,
      output,
      csv;
  private String letter_template_file, email_template_file, output_dir, csv_dir;

  /**
   * Constructs a new CommandHandler object with default values
   */
  public CommandHandler() {
    this.email = false;
    this.email_template = false;
    this.letter = false;
    this.letter_template = false;
    this.output = false;
    this.csv = false;
  }

  /**
   * Handles user input and generate messages based on input
   * @param input a String of user input
   * @throws Exception an Exception with error message telling which part of the input is invalid
   */
  public void handleInput(String input) throws Exception {
    //First make sure that the input is correct
    try {
      verifyInput(input);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    //Retrieves the csv file that has every customer's information to create messages
    try{
      directory = new CustomerDirectory(csv_dir);
    }
    catch (Exception e){
      throw new Exception(e.getMessage());
    }

    //If the user specifies that emails need to be created, create emails
    if (email) {
      templateHandler = new EmailTemplateHandler();
      templateHandler.getTemplate(email_template_file);
      for (Customer c : directory.getCustomerList()) {
        try {
          templateHandler.generateMessage(c, output_dir);
        }
        catch(Exception e){
          throw new Exception(e.getMessage());
        }
      }
    }

    //If the user specifies that letters need to be created, create letters
    if (letter) {
      templateHandler = new LetterTemplateHandler();
      templateHandler.getTemplate(letter_template_file);
      for (Customer c : directory.getCustomerList()) {
        try {
          templateHandler.generateMessage(c, output_dir);
        }
        catch(Exception e){
          throw new Exception(e.getMessage());
        }
      }
    }
  }

  /**
   * Verifies that the user input is sufficient for the program to generate messages
   * @param input a String of the user input
   * @throws Exception an Exception with error message telling which part of the input is invalid
   */
  private void verifyInput(String input) throws Exception {
    String[] splitted = input.split("--");
    for (int i = 1; i < splitted.length; i++) {
      String s = splitted[i].trim();
      if (s.equals(USAGE_EMAIL)) {
        email = true;
        continue;
      }
      if (s.equals(USAGE_LETTER)) {
        letter = true;
        continue;
      }

      if (s.indexOf(USAGE_EMAIL_TEMPLATE) == 0) {
        email_template = true;

        //Check if the email template file name is specified
        try {
          email_template_file = s.split(" ")[1];
        } catch (IndexOutOfBoundsException e) {
          throw new IndexOutOfBoundsException(String.format("%s\n%s", ERROR_NO_EMAIL_TEMPLATE_DIR, USAGE));
        }
        continue;
      }

      if (s.indexOf(USAGE_LETTER_TEMPLATE) == 0) {
        letter_template = true;

        //Check if the letter template file name is specified
        try {
          letter_template_file = s.split(" ")[1];
        } catch (IndexOutOfBoundsException e) {
          throw new IndexOutOfBoundsException(String.format("%s\n%s", ERROR_NO_LETTER_TEMPLATE_DIR, USAGE));
        }
        continue;
      }

      if (s.indexOf(USAGE_CSV) == 0) {
        csv = true;

        //Check if the csv file name is specified
        try {
          csv_dir = s.split(" ")[1];
        } catch (IndexOutOfBoundsException e) {
          throw new IndexOutOfBoundsException(String.format("%s\n%s", ERROR_NO_CSV_DIR, USAGE));
        }
        continue;
      }

      if (s.indexOf(USAGE_OUTPUT) == 0) {
        output = true;

        //Check if the output directory name is specified
        try {
          output_dir = s.split(" ")[1];
        } catch (IndexOutOfBoundsException e) {
          throw new IndexOutOfBoundsException(String.format("%s\n%s", ERROR_NO_OUTPUT_DIR, USAGE));
        }
        continue;
      }

      //If none of the previous command patterns match, there seems to be an illegal input
      throw new Exception(String.format("%s\n%s", ERROR_NO_EMAIL_TEMPLATE_DIR, USAGE));
    }

    //Check if the user's arguments are combined correctly

    if (letter && !letter_template) {
      throw new Exception(String.format("%s\n%s", ERROR_NO_LETTER_TEMPLATE, USAGE));
    }

    if (email && !email_template) {
      throw new Exception(String.format("%s\n%s", ERROR_NO_EMAIL_TEMPLATE, USAGE));
    }

    if (!csv) {
      throw new Exception(String.format("%s\n%s", ERROR_NO_CSV, USAGE));
    }

    if (!output) {
      throw new Exception(String.format("%s\n%s", ERROR_NO_OUTPUT, USAGE));
    }
  }

  /**
   *
   * @param o another CommandHandler object
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
    CommandHandler that = (CommandHandler) o;
    return Objects.equals(templateHandler, that.templateHandler)
        && Objects.equals(directory, that.directory);
  }

  /**
   *
   * @return an Integer represents the object's hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(templateHandler, directory);
  }
}
