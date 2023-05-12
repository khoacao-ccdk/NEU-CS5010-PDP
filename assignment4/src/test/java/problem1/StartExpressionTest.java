package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StartExpressionTest {

  private List<String> a1;
  private List<String> a2;
  private List<String> a3;
  private String k1, k2, k3;
  private NonTerminalExpression n1, n2, n3;
  private ExpressionDirectory ex;
  private StartExpression s;
  private List<String> template;
  private Random r;
  @BeforeEach
  void setUp() {
    r=new Random(100);
    ex = new ExpressionDirectory();
    a1 = new ArrayList<>();
    a2 = new ArrayList<>();
    a3 = new ArrayList<>();
    a1.add("waves");
    a1.add("big yellow flowers");
    a1.add("slugs");
    k1 = "object";
    n1 = new NonTerminalExpression(k1, a1, 100);
    a2.add("sigh <adverb>");
    a2.add("portend like <object>");
    a2.add("die <adverb>");
    k2 = "verb";
    n2 = new NonTerminalExpression(k2, a2, 100);
    a3.add("warily");
    a3.add("grumpily");
    k3 = "adverb";
    n3 = new NonTerminalExpression(k3, a3, 100);
    ex.put(k1, n1);
    ex.put(k2, n2);
    ex.put(k3, n3);
    template = new ArrayList<>();
    template.add("The <object> <verb> tonight.");
    s = new StartExpression(template,100);
  }

  @Test
  void generateOutput() throws Exception {
    Assertions.assertEquals("The big yellow flowers portend like big yellow flowers tonight.",
        s.generateOutput(ex,100));
  }

  @Test
  void testEquals() {
    StartExpression s1 = new StartExpression(template);
    Assertions.assertEquals(true, s.equals(s1));
    Assertions.assertEquals(false, s.equals(""));
    Assertions.assertEquals(false, s.equals(template));

    assertNotEquals(s, null);
    assertEquals(s, s);
  }

  @Test
  void testHashCode() {
    StartExpression s1 = new StartExpression(template);
    Assertions.assertEquals(s1.hashCode(), s.hashCode());
  }

  @Test
  void testToString() {
    StartExpression s1 = new StartExpression(template);
    Assertions.assertEquals(s1.toString(), s.toString());
  }
}