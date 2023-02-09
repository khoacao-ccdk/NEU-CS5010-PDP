import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing Author class functions, including getName(), getEmail(), and getAddress().
 */
class AuthorTest {

  /**
   * Creates a new Author with information before each test.
   */
  @BeforeEach
  void setUp() {
    testAuthor = new Author("John Doe", "johndoe@gmail.com", "225 Terry Ave N");
  }

  /**
   * Tests if the getName() function returns the expected value.
   */
  @Test
  void getName() {
    assertEquals("John Doe", this.testAuthor.getName());
  }

  /**
   * Tests if the getEmail() function returns the expected value.
   */
  @Test
  void getEmail() {
    assertEquals("johndoe@gmail.com", this.testAuthor.getEmail());
  }

  /**
   * Tests if the getAddress() function returns the expected value.
   */
  @Test
  void getAddress() {
    assertEquals("225 Terry Ave N", this.testAuthor.getAddress());
  }

  private Author testAuthor;
}