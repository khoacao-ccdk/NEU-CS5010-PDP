package problem2.Car;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.Car.NewCar;
import problem2.MakeModel;

class NewCarTest {
  NewCar car;
  MakeModel acura;

  @BeforeEach
  void setUp() {
    acura = new MakeModel("Acura", "TLX");
    car = new NewCar(
        "123456",
        2019,
        acura,
        30000,
        3
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
  void getNumAvailable() {
    assertEquals(3, car.getNumAvailable());
  }

  @Test
  void setNumAvailable() {
    car.setNumAvailable(0);
    assertEquals(0, car.getNumAvailable());
  }
}