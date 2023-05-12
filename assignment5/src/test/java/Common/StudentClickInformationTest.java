package Common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentClickInformationTest {
  StudentClickInformation click;

  @BeforeEach
  void setUp() {
     click = new StudentClickInformation("Test",
        "01",
        10,
        20);
  }

  @Test
  void getCodeModule() {
    assertEquals("Test", click.getCodeModule());
  }

  @Test
  void setCodeModule() {
    click.setCodeModule("");
    assertEquals("", click.getCodeModule());
  }

  @Test
  void getCodePresentation() {
    assertEquals("01", click.getCodePresentation());
  }

  @Test
  void setCodePresentation() {
    click.setCodePresentation("0");
    assertEquals("0", click.getCodePresentation());
  }

  @Test
  void getDate() {
    assertEquals(10, click.getDate());
  }

  @Test
  void setDate() {
    click.setDate(-1);
    assertEquals(-1, click.getDate());
  }

  @Test
  void getNumClicks() {
    assertEquals(20, click.getNumClicks());
  }

  @Test
  void setNumClicks() {
    click.setNumClicks(1);
    assertEquals(1, click.getNumClicks());
  }

  @Test
  void testEquals() {
    assertEquals(click, new StudentClickInformation("Test",
        "01",
        10,
        20));
    assertEquals(click, click);
    assertNotEquals(click, null);
    assertNotEquals(click, new String());
    assertNotEquals(click, new StudentClickInformation("Test",
        "0",
        10,
        20));
    assertNotEquals(click, new StudentClickInformation("",
        "01",
        10,
        20));
    assertNotEquals(click, new StudentClickInformation("Test",
        "01",
        1,
        20));
    assertNotEquals(click, new StudentClickInformation("Test",
        "01",
        10,
        2));
  }

  @Test
  void testHashCode() {
    assertEquals(new StudentClickInformation("Test",
        "01",
        10,
        20).hashCode(),
        click.hashCode());
  }

  @Test
  void testToString() {
    StringBuffer sb = new StringBuffer("StudentClickInformation{");
    sb.append("codeModule='").append("Test").append('\'');
    sb.append(", codePresentation='").append("01").append('\'');
    sb.append(", date=").append(10);
    sb.append(", numClicks=").append(20);
    sb.append('}');

    assertEquals(sb.toString(), click.toString());
  }
}