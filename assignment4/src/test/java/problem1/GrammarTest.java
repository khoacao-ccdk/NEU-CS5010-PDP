package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.Reader.JSONReader;

class GrammarTest {

  JSONReader reader;
  ExpressionDirectory directory;

  String grammarFilePath;
  Grammar g;

  @BeforeEach
  void setUp() throws Exception{
    reader = new JSONReader();
    try {
      g = reader.getGrammarList("grammars").get(1);
      reader.getGrammarBody(g);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    URL url = this.getClass().getClassLoader().getResource("grammars");
    File grammarFolder = new File(url.toURI());
    File[] fileList = grammarFolder.listFiles();
    grammarFilePath = fileList[1].getPath();

    directory = new ExpressionDirectory();
    directory.put("object",
        new NonTerminalExpression("object", List.of("waves", "big yellow flowers", "slugs")));
    directory.put("verb",
        new NonTerminalExpression("verb", List.of("sigh <adverb>", "portend like <object>", "die <adverb>")));
    directory.put("adverb",
        new NonTerminalExpression("adverb", List.of("warily", "grumpily")));
  }

  @Test
  void getStartExpression() {
    Expression expected = new StartExpression(List.of("The <object> <verb> tonight."));

    assertEquals(expected, g.getStartExpression());
  }

  @Test
  void getExpressionDirectory() {
    System.out.println(g.getExpressionDirectory().getAllData());
    assertEquals(directory, g.getExpressionDirectory());
  }

  @Test
  void getGrammarTitle() {
    assertEquals("Poem Generator", g.getGrammarTitle());
  }

  @Test
  void getGrammarDescription() {
    assertEquals("A grammar that generates poems. ", g.getGrammarDescription());
  }

  @Test
  void setStartExpression() {
    g.setStartExpression(null);
    assertNull(g.getStartExpression());
  }

  @Test
  void setExpressionDirectory() {
    g.setExpressionDirectory(null);
    assertNull(g.getExpressionDirectory());
  }

  @Test
  void getFilePath(){
    assertEquals(grammarFilePath, g.getFilePath());
  }

  @Test
  void testEquals() {
    Grammar equal = new Grammar("Poem Generator", "A grammar that generates poems. ", g.getFilePath());
    equal.setStartExpression(new StartExpression(List.of("The <object> <verb> tonight.")));
    equal.setExpressionDirectory(directory);

    assertEquals(g, g);
    assertEquals(g, equal);
    assertNotEquals(g, null);
    assertNotEquals(g, new String());
  }

  @Test
  void testHashCode() {
    Grammar equal = new Grammar("Poem Generator", "A grammar that generates poems. ", g.getFilePath());
    equal.setStartExpression(new StartExpression(List.of("The <object> <verb> tonight.")));
    equal.setExpressionDirectory(directory);

    assertEquals(equal.hashCode(), g.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("Grammar{");
    sb.append("startExpression=").append(new StartExpression(List.of("The <object> <verb> tonight.")));
    sb.append(", expressionDirectory=").append(directory);
    sb.append(", grammarTitle='").append("Poem Generator").append('\'');
    sb.append(", grammarDescription='").append("A grammar that generates poems. ").append('\'');
    sb.append(", filePath='").append(grammarFilePath).append('\'');
    sb.append('}');

    assertEquals(sb.toString(), g.toString());
  }
}