import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerDirectoryTest {
  CustomerDirectory d;

  @BeforeEach
  void setUp() throws Exception {
    d = new CustomerDirectory("insurance-company-members.csv");
  }

  @Test
  void readEmptyFile(){
    assertDoesNotThrow(() -> new CustomerDirectory("blank-file-for-test.csv"));
  }

  @Test
  void invalidFileName() {
    Throwable exception = assertThrows(Exception.class,
        () -> new CustomerDirectory("invalid_file.csv"));
    assertEquals(
        CustomerDirectory.ERROR_CSV_NOT_FOUND,
        exception.getMessage());
  }

  @Test
  void getCustomerList() {
    assertEquals(500, d.getCustomerList().size());
  }

  @Test
  void testEquals() throws Exception {
    assertEquals(new CustomerDirectory("insurance-company-members.csv"), d);
    assertEquals(d, d);
    assertNotEquals(d, null);
    assertNotEquals(d, new String());
    try {
      assertNotEquals(new CustomerDirectory("blank-file-for-test.csv"), d);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  @Test
  void testHashCode() throws Exception {
    assertEquals(new CustomerDirectory("insurance-company-members.csv").hashCode(),
        d.hashCode());
    assertNotEquals(new CustomerDirectory("blank-file-for-test.csv").hashCode(), d.hashCode());
  }
}