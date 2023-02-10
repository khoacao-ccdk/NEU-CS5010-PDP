package problem2.Vessel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem2.MakeModel;
import problem2.Vessel.Boat;
import problem2.Vessel.PropulsionType;

class BoatTest {

  Boat boat;
  MakeModel yamaha;

  @BeforeEach
  void setUp() {
    yamaha = new MakeModel("Yamaha", "SR7500");
    boat = new Boat(
        "123456",
        2019,
        yamaha,
        30000,
        25,
        8,
        PropulsionType.INBOARD_ENGINE
    );
  }

  @Test
  void getId() {
    assertSame("123456", boat.getId());
  }

  @Test
  void getYear() {
    assertEquals(2019, boat.getYear());
  }

  @Test
  void setYear() {
    boat.setYear(2020);
    assertEquals(2020, boat.getYear());
  }

  @Test
  void getMakeModel() {
    assertSame(yamaha, boat.getMakeModel());
  }

  @Test
  void setMakeModel() {
    MakeModel gulfstar = new MakeModel("Gulfstar", "41");
    boat.setMakeModel(gulfstar);
    assertSame(gulfstar, boat.getMakeModel());
  }

  @Test
  void getMsrp() {
    assertEquals(30000, boat.getMsrp());
  }

  @Test
  void setMsrp() {
    boat.setMsrp(40000);
    assertEquals(40000, boat.getMsrp());
  }

  @Test
  void getLength() {
    assertEquals(25, boat.getLength());
  }

  @Test
  void setLength() {
    boat.setLength(30);
    assertEquals(30, boat.getLength());
  }

  @Test
  void getNumPassenger() {
    assertEquals(8, boat.getNumPassenger());
  }

  @Test
  void setNumPassenger() {
    boat.setNumPassenger(10);
    assertEquals(10, boat.getNumPassenger());
  }

  @Test
  void getPropType() {
    assertEquals(PropulsionType.INBOARD_ENGINE, boat.getPropType());
  }

  @Test
  void setPropType() {
    boat.setPropType(PropulsionType.SAIL_POWER);
    assertEquals(PropulsionType.SAIL_POWER, boat.getPropType());
  }
}