import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHandlerTest {

  CommandHandler handler;

  @BeforeEach
  void setUp() {
    handler = new CommandHandler();
  }

  @Test
  void handleInput_Correct() {
    //email
    String input = "--csv-file insurance-company-members.csv --email-template email-template.txt --output-dir emails --email";
    assertDoesNotThrow(() -> handler.handleInput(input));
    File file = new File("./emails/Abel_Maclead_email.txt");
    assertTrue(file.exists());

    //letter
    String input2 = "--csv-file insurance-company-members.csv --letter-template letter-template.txt --output-dir letters --letter";
    assertDoesNotThrow(() -> handler.handleInput(input2));
    file = new File("./letters/Abel_Maclead_letter.txt");
    assertTrue(file.exists());
  }

  @Test
  void handleInput_NoCSV() {
    String input = "--email-template email-template.txt --output-dir emails --email";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_CSV, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoCSVFileName() {
    String input = "--csv-file --email-template email-template.txt --output-dir emails --email";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_CSV_DIR, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoOuptut() {
    String input = "--csv-file insurance-company-members.csv --email-template email-template.txt --email";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_OUTPUT, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoOutputFileName() {
    String input = "--csv-file insurance-company-members.csv --email-template email-template.txt --output-dir --email";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_OUTPUT_DIR, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoEmailTemplate() {
    String input = "--csv-file insurance-company-members.csv --output-dir emails --email";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_EMAIL_TEMPLATE, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoEmailTemplateFileName() {
    String input = "--csv-file insurance-company-members.csv --email-template --output-dir emails --email";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_EMAIL_TEMPLATE_DIR, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoLetterTemplate() {
    String input = "--csv-file insurance-company-members.csv --output-dir emails --letter";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_LETTER_TEMPLATE, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void handleInput_NoLetterTemplateFileName() {
    String input = "--csv-file insurance-company-members.csv --letter-template --output-dir emails --letter";
    Throwable exception = assertThrows(Exception.class,
        () -> handler.handleInput(input));
    assertEquals(
        String.format("%s\n%s", CommandHandler.ERROR_NO_LETTER_TEMPLATE_DIR, CommandHandler.USAGE),
        exception.getMessage());
  }

  @Test
  void testEquals() {
    assertEquals(handler, handler);
    assertEquals(new CommandHandler(), handler);
    assertNotEquals(handler, null);
    assertNotEquals(handler, new String());
  }

  @Test
  void testHashCode() {
    assertEquals(new CommandHandler().hashCode(), handler.hashCode());
  }
}