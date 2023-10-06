package Message;

import static org.junit.jupiter.api.Assertions.*;

import Message.Insult.InsultGenerator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsultGeneratorTest {
  InsultGenerator g1, g2;

  @BeforeEach
  void setUp() throws Exception{
    g1 = new InsultGenerator(1000);
    g2 = new InsultGenerator();
  }

  @Test
  void getInsult() {
    assertDoesNotThrow(() -> {
      String res = g1.getInsult();
      assertEquals("Do you still love nature, despite what it did to you?", res);
    });
  }

  @Test
  void testEquals() throws Exception{
    assertEquals(g2, g2);
    assertEquals(g2, new InsultGenerator());
    assertEquals(g1, new InsultGenerator(1000));
    assertNotEquals(g2, null);
    assertNotEquals(g2, new String());
  }

  @Test
  void testHashCode() throws Exception{
    assertEquals(g1, new InsultGenerator());
  }

  @Test
  void testToString() {
    List<String> insults = List.of(
        "You mutilated goat.",
        "You are proof that evolution CAN go in reverse.",
        "Do you still love nature, despite what it did to you?",
        "There's only one problem with your face, I can see it.",
        "You have the perfect face for radio.");
    final StringBuffer sb = new StringBuffer("InsultGenerator{");
    sb.append("insults=").append(insults);
    sb.append('}');
    assertEquals(sb.toString(), g2.toString());
  }
}