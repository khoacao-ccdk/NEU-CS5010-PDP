package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.Reader.JSONReader;

class JSONReaderTest {

  JSONReader reader;

  @BeforeEach
  void setUp() {
    reader = new JSONReader();

    try {
      reader.getGrammarList("grammars");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void getGrammarList() {
    String jsonDirectory = "grammars";

    reader = new JSONReader();
    assertDoesNotThrow(() -> reader.getGrammarList(jsonDirectory));

    //Branch coverage
    assertDoesNotThrow(() -> reader.getGrammarList(jsonDirectory));

    //An invalid directory name is given
    String invalidDirectory = "N/A";
    reader = new JSONReader();
    Throwable exception = assertThrows(Exception.class,
        () -> reader.getGrammarList(invalidDirectory));
    assertEquals(
        JSONReader.ERROR_COULD_NOT_READ_DIRECTORY,
        exception.getMessage());

    //There is an invalid file format in the folder
    String invalidFileDirectory = "TestFiles";
    reader = new JSONReader();
    exception = assertThrows(Exception.class,
        () -> reader.getGrammarList(invalidFileDirectory));
    assertEquals(
        JSONReader.ERROR_COULD_NOT_READ_JSON,
        exception.getMessage());
  }

  @Test
  void getGrammarBody(){
    Grammar grammar = null;
    try {
      grammar = reader.getGrammarList("grammars").get(1);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Grammar finalGrammar = grammar;
    assertDoesNotThrow(() -> reader.getGrammarBody(finalGrammar));
    assertDoesNotThrow(() -> finalGrammar.getExpressionDirectory().getSubData("verb"));
  }

  @Test
  void testEquals() {
    JSONReader equal = new JSONReader(), diff = new JSONReader();
    try {
      equal.getGrammarList("grammars");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    assertEquals(reader, equal);
    assertEquals(reader, reader);
    assertNotEquals(reader, null);
    assertNotEquals(reader, new String());
    assertNotEquals(reader, diff);
  }

  @Test
  void testHashCode() {
    JSONReader equal = new JSONReader();
    try {
      equal.getGrammarList("grammars");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    assertEquals(equal.hashCode(), reader.hashCode());
  }
}