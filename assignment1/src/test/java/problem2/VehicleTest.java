package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.Car.UsedCar;
import problem2.MakeModel;
import problem2.Vehicle;
import problem2.Vessel.Boat;
import problem2.Vessel.PropulsionType;

class VehicleTest {
  Vehicle car, boat;
  MakeModel honda, yamaha;
  @BeforeEach
  void setUp() {
    honda = new MakeModel("Honda", "Accord");
    yamaha = new MakeModel("Yamaha", "AR250");
    car = new UsedCar("123456",
        2019,
        honda,
        30000,
        50000,
        1,
        0);

    boat = new Boat("121212",
        2010,
        yamaha,
        100000,
        25,
        8,
        PropulsionType.OUTBOARD_ENGINE);
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
    assertSame(honda, car.getMakeModel());
  }

  @Test
  void setMakeModel() {
    car.setMakeModel(yamaha);
    assertSame(yamaha, car.getMakeModel());
  }

  @Test
  void getMsrp() {
    assertEquals(100000, boat.getMsrp());
  }

  @Test
  void setMsrp() {
    boat.setMsrp(200000);
    assertEquals(200000, boat.getMsrp());
  }
}