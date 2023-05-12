package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NonTerminalExpressionTest {

  private List<String> a1;
  private List<String> a2;
  private List<String> a3;
  private String k1, k2, k3;
  private NonTerminalExpression n1, n2, n3;
  private ExpressionDirectory ex;
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
    n1 = new NonTerminalExpression(k1, a1);
    a2.add("sigh <adverb>");
    a2.add("portend like <object>");
    a2.add("die <adverb>");
    k2 = "verb";
    n2 = new NonTerminalExpression(k2, a2, 100);
    a3.add("warily");
    a3.add("grumpily");
    k3 = "adverb";
    n3 = new NonTerminalExpression(k3, a3);
    ex.put(k1, n1);
    ex.put(k2, n2);
    ex.put(k3, n3);

  }

  @Test
  void generateOutput() throws Exception {
    Assertions.assertEquals("portend like big yellow flowers", n2.generateOutput(ex));
  }

  @Test
  void testEquals(){
    assertEquals(n1, new NonTerminalExpression(k1, a1));
    assertEquals(n1, n1);

    assertNotEquals(n1, n2);
    assertNotEquals(n1, null);
    assertNotEquals(n1, new String());
  }

  @Test
  void testToString(){
    assertEquals(new NonTerminalExpression(k1, a1).hashCode(), n1.hashCode());
  }
}