import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Customer.*;

class EmailTemplateHandlerTest {

  TemplateHandler handler;

  @BeforeEach
  void setUp() {
    handler = new EmailTemplateHandler();
  }

  @Test
  void getTemplate() throws Exception {
    handler.getTemplate("email-template.txt");
    assertDoesNotThrow(() -> handler.getTemplate("email-template.txt"));

    //Getting invalid template
    Throwable exception = assertThrows(Exception.class,
        () -> handler.getTemplate("invalid-file.txt"));
    assertEquals(
        EmailTemplateHandler.ERROR_TEMPLATE_NOT_FOUND,
        exception.getMessage());
  }

  @Test
  void generateMessage() throws Exception {
    Address a = new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109");
    ContactInformation c = new ContactInformation("1234561234", "1234564567", "test@gmail.com",
        "test.com");
    Name n = new Name("Cody", "Cao");
    Customer customer = new Customer(n, "NEU", a, c);

    handler.getTemplate("letter-template.txt");
    handler.generateMessage(customer, "test-output");
    File file = new File("./test-output/Cody_Cao_email.txt");
    assertTrue(file.exists());
  }

  @Test
  void generateMessageUnsuccess() throws Exception {
    Address a = new Address("225 Terry Ave N", "Seattle", "King", "WA", "98109");
    ContactInformation c = new ContactInformation("1234561234", "1234564567", "test@gmail.com",
        "test.com");
    Name n = new Name("Cody", "Cao");
    Customer customer = new Customer(n, "NEU", a, c);

    //Unsuccessful creation of the message
    handler.getTemplate("email-template.txt");
    Throwable exception = assertThrows(Exception.class,
        () -> handler.generateMessage(customer, null));
    assertEquals(
        EmailTemplateHandler.ERROR_CANNOT_CREATE,
        exception.getMessage());
  }

  @Test
  void testEquals() throws Exception {
    assertEquals(handler, handler);
    assertEquals(new EmailTemplateHandler(), handler);
    assertNotEquals(handler, null);
    assertNotEquals(handler, new String());

    TemplateHandler diff = new EmailTemplateHandler();
    diff.getTemplate("blank-template-file-for-test.txt");
    assertNotEquals(diff, handler);
  }

  @Test
  void testHashCode() throws Exception {
    assertEquals(new LetterTemplateHandler().hashCode(), handler.hashCode());

    TemplateHandler diff = new LetterTemplateHandler();
    diff.getTemplate("email-template.txt");
    assertNotEquals(diff.hashCode(), handler.hashCode());
  }
}