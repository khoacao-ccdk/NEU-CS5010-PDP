import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactInfoTest {
  ContactInfo contact;
  Name name;
  @BeforeEach
  void setUp() {
    name = new Name("Cody", "Cao");
    contact = new ContactInfo(
        name,
        "225 Terry Ave N",
        "1234561234",
        "test@gmail.com"
        );
  }

  @Test
  void getName() {
    assertSame(name, contact.getName());
  }

  @Test
  void setName() {
    Name test = new Name("Khoa", "Cao");
    contact.setName(test);
    assertSame(test, contact.getName());
  }

  @Test
  void getAddress() {
    assertSame("225 Terry Ave N", contact.getAddress());
  }

  @Test
  void setAddress() {
    contact.setAddress("Some address");
    assertSame("Some address", contact.getAddress());
  }

  @Test
  void getPhone() {
    assertSame("1234561234", contact.getPhone());
  }

  @Test
  void setPhone() {
    contact.setPhone("1234567890");
    assertSame("1234567890", contact.getPhone());
  }

  @Test
  void getEmail() {
    assertSame("test@gmail.com", contact.getEmail());
  }

  @Test
  void setEmail() {
    contact.setEmail("test2@gmail.com");
    assertSame("test2@gmail.com", contact.getEmail());
  }

  @Test
  void equals(){
    assertTrue(new ContactInfo(
        name,
        "225 Terry Ave N",
        "1234561234",
        "test@gmail.com"
    ).equals(contact));
  }

  @Test
  void equalsNullFields(){
    assertFalse(contact.equals(null));
    assertFalse(contact.equals(new ContactInfo(
        null, null, null, null
    )));
  }

  @Test
  void testHashCode(){
    assertEquals(new ContactInfo(
        name,
        "225 Terry Ave N",
        "1234561234",
        "test@gmail.com"
    ).hashCode(), contact.hashCode());
  }
}