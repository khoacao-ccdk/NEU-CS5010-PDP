package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.Reader.JSONReader;
import problem1.Reader.Reader;

class SentenceGeneratorTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;
  private final InputStream originalIn = System.in;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
    System.setIn(originalIn);
  }

  @Test
  void main() {
    System.setIn(new ByteArrayInputStream("1\nn\nq\n".getBytes()));
    assertDoesNotThrow(() -> SentenceGenerator.main(new String[]{"grammars"}));
  }

  @Test
  void mainGeneratorException() {
    System.setIn(new ByteArrayInputStream("3\nq\n".getBytes()));
    SentenceGenerator.main(new String[]{"grammars"});

    String expected = new String("Loading grammars..." + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)" + System.lineSeparator()
        + "Terminal body1 not found in JSON file" + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)") + System.lineSeparator();

    assertEquals(
        expected,
        outContent.toString());
  }

  @Test
  void mainInvalidDirectoryException() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    SentenceGenerator.main(new String[]{"N/A"});

    String expected = new String("Loading grammars..." + System.lineSeparator()
        + Reader.ERROR_COULD_NOT_READ_DIRECTORY + System.lineSeparator());

    assertEquals(
        expected,
        outContent.toString());
  }

  @Test
  void mainInvalidJSONFile() {
    System.setIn(new ByteArrayInputStream("".getBytes()));
    SentenceGenerator.main(new String[]{"TestFiles"});

    String expected = new String("Loading grammars..." + System.lineSeparator()
        + JSONReader.ERROR_COULD_NOT_READ_JSON + System.lineSeparator());

    assertEquals(
        expected,
        outContent.toString());
  }

  @Test
  void mainInvalidNumberInput() {
    System.setIn(new ByteArrayInputStream("4\nq\n".getBytes()));
    SentenceGenerator.main(new String[]{"grammars"});

    String expected = new String("Loading grammars..." + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)" + System.lineSeparator()
        + SentenceGenerator.ERROR_INVALID_NUMBER_INPUT + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)") + System.lineSeparator();

    assertEquals(
        expected,
        outContent.toString());
  }

  @Test
  void mainInvalidCharacterInput() {
    System.setIn(new ByteArrayInputStream("invalid\nq\n".getBytes()));
    SentenceGenerator.main(new String[]{"grammars"});

    String expected = new String("Loading grammars..." + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)" + System.lineSeparator()
        + SentenceGenerator.ERROR_INVALID_NUMBER_INPUT + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)") + System.lineSeparator();

    assertEquals(
        expected,
        outContent.toString());
  }

  @Test
  void mainCouldNotGenerateOutput() {
    System.setIn(new ByteArrayInputStream("4\nq\n".getBytes()));
    SentenceGenerator.main(new String[]{"grammars"});

    String expected = new String("Loading grammars..." + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)" + System.lineSeparator()
        + SentenceGenerator.ERROR_INVALID_NUMBER_INPUT + System.lineSeparator()
        + "The following grammars are available:" + System.lineSeparator()
        + "1. Insult Generator" + System.lineSeparator()
        + "2. Poem Generator" + System.lineSeparator()
        + "3. Term Paper Generator" + System.lineSeparator()
        + "Which grammar would you like to use? (q to quit)") + System.lineSeparator();

    assertEquals(
        expected,
        outContent.toString());
  }
}