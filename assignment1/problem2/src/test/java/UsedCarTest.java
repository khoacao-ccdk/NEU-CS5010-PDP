import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsedCarTest {

  UsedCar car;
  MakeModel acura;

  @BeforeEach
  void setUp() {
    acura = new MakeModel("Acura", "TLX");
    car = new UsedCar(
        "123456",
        2019,
        acura,
        30000,
        30000,
        1,
        0
    );
  }

  @Test
  void getId() {
    assertSame("123456", car.getId());
  }

  @Test
  void getYear() {
    assertEquals(2019, car.getYear());
  }

  @Test
  void setYear() {
    car.setYear(2020);
    assertEquals(2020, car.getYear());
  }

  @Test
  void getMakeModel() {
    assertSame(acura, car.getMakeModel());
  }

  @Test
  void setMakeModel() {
    MakeModel honda = new MakeModel("Honda", "CR-V");
    car.setMakeModel(honda);
    assertSame(honda, car.getMakeModel());
  }

  @Test
  void getMsrp() {
    assertEquals(30000, car.getMsrp());
  }

  @Test
  void setMsrp() {
    car.setMsrp(40000);
    assertEquals(40000, car.getMsrp());
  }

  @Test
  void getMileage() {
    assertEquals(30000, car.getMileage());
  }

  @Test
  void setMileage() {
    car.setMileage(10000);
    assertEquals(10000, car.getMileage());
  }

  @Test
  void getNumOwners() {
    assertEquals(1, car.getNumOwners());
  }

  @Test
  void setNumOwners() {
    car.setNumOwners(2);
    assertEquals(2, car.getNumOwners());
  }

  @Test
  void getNumAccidents() {
    assertEquals(0, car.getNumAccidents());
  }

  @Test
  void setNumAccidents() {
    car.setNumAccidents(2);
    assertEquals(2, car.getNumAccidents());
  }
}