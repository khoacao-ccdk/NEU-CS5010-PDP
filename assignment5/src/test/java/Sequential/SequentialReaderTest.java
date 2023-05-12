package Sequential;


import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SequentialReaderTest {
private SequentialReader a;
private List d;
  @BeforeEach
  void setUp()  {
    a = new SequentialReader<SequentialCourse>("courses.csv", SequentialCourse.class);

  }

  @Test
  void setUpParser() throws Exception {
    a.setUpParser();
    SequentialCourse course = (SequentialCourse) a.nextLine();
    Assertions.assertEquals("AAA",course.getCodeModule());
  }

  @Test
  void getFilepath() {
    Assertions.assertEquals("courses.csv",a.getFilepath());
  }

  @Test
  void getBeanClass() {
    Assertions.assertEquals(SequentialCourse.class,a.getBeanClass());
  }

  @Test
  void getData() throws Exception {
    a.setUpParser();
    Assertions.assertEquals(22,a.getData().size());
  }



  @Test
  void getIter() throws Exception {
    a.setUpParser();
    Assertions.assertEquals(true,a.getIter().hasNext());
  }

  @Test
  void nextLine() throws Exception {
    a.setUpParser();

    Assertions.assertEquals(a.getData().iterator().next(),a.nextLine());
  }

  @Test
  void testEquals() {
    SequentialReader b=new SequentialReader<SequentialCourse>("courses.csv", SequentialCourse.class);
    SequentialReader b1=new SequentialReader<SequentialCourse>("studentVleSmall.csv", SequentialCourse.class);
    Assertions.assertEquals(true,a.equals(b));
    Assertions.assertEquals(true,a.equals(a));
    Assertions.assertEquals(false,a.equals(""));
    Assertions.assertEquals(false,a.equals("a"));
    Assertions.assertEquals(false,a.equals(b1));
  }

  @Test
  void testHashCode() {
    SequentialReader b=new SequentialReader<SequentialCourse>("courses.csv", SequentialCourse.class);
    Assertions.assertEquals(b.hashCode(),a.hashCode());

  }

  @Test
  void testToString() {
    SequentialReader b=new SequentialReader<SequentialCourse>("courses.csv", SequentialCourse.class);
    Assertions.assertEquals(b.toString(),a.toString());
  }
}