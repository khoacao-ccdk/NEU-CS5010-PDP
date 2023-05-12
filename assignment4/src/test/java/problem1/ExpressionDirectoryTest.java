package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressionDirectoryTest {

  private NonTerminalExpression a1;
  private NonTerminalExpression a2;
  private ExpressionDirectory a;
  private List<String> d1;
  private List<String> d2;

  @BeforeEach
  void setUp() {
    d1 = new ArrayList<>();
    d1.add("a");
    d1.add("b");
    d2 = new ArrayList<>();
    d2.add("a");
    a = new ExpressionDirectory();
    a1 = new NonTerminalExpression("asd", d1);
    a2 = new NonTerminalExpression("bd", d2);
    a.put("a", a1);
    a.put("b", a2);
  }

  @Test
  void getAllData() {
    Assertions.assertEquals(a1, a.getAllData().get("A"));
    Assertions.assertEquals(a1, a.getAllData().get("a"));
  }

  @Test
  void put() throws Exception {
    a.put("A", a2);
    Assertions.assertEquals(a2, a.getSubData("a"));
    Assertions.assertEquals(a2, a.getSubData("A"));
  }

  @Test
  void getSubData() throws Exception {
    Assertions.assertEquals(a1, a.getSubData("A"));
    Assertions.assertEquals(a1, a.getSubData("a"));

    Throwable exception = assertThrows(Exception.class,
        () -> a.getSubData("N/A"));
    assertEquals(
        String.format(ExpressionDirectory.ERROR_TERMINAL_NOT_FOUND, "N/A"),
        exception.getMessage());
  }

  @Test
  void testEquals() {
    ExpressionDirectory equal = new ExpressionDirectory();
    equal.put("a", a1);
    equal.put("b", a2);

    assertEquals(a, a);
    assertEquals(a, equal);
    assertNotEquals(a, null);
    assertNotEquals(a, new String());
    assertNotEquals(a, new ExpressionDirectory());
  }

  @Test
  void testHashcode() {
    ExpressionDirectory equal = new ExpressionDirectory();
    equal.put("a", a1);
    equal.put("b", a2);

    assertEquals(equal.hashCode(), a.hashCode());
  }
}