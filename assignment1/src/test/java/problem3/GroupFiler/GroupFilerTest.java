package problem3.GroupFiler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem3.ContactInfo;
import problem3.GroupFiler.GroupFiler;
import problem3.Name;

class GroupFilerTest {

  GroupFiler f;
  ContactInfo c;
  Name n;

  @BeforeEach
  void setUp() {
    n = new Name("Cody", "Cao");
    c = new ContactInfo(n, "225 Terry Ave", "1234567890", "test@gmail.com");
    f = new HeadOfHousehold(
        "12345",
        c,
        250000,
        30000,
        10000,
        25000,
        10000,
        30000,
        15000,
        3000,
        2,
        2,
        3000,
        0
    );
  }

  @Test
  void getId() {
    assertSame("12345", f.getId());
  }

  @Test
  void getContact() {
    assertTrue(
        new ContactInfo(n, "225 Terry Ave", "1234567890", "test@gmail.com")
            .equals(f.getContact()));
  }

  @Test
  void setContact() {
    f.setContact(new ContactInfo(n, "401 Terry Ave", "1234567891", "test2@gmail.com"));
    assertTrue(
        new ContactInfo(n, "401 Terry Ave", "1234567891", "test2@gmail.com")
            .equals(f.getContact()));
  }

  @Test
  void getEarning() {
    assertEquals(250000, f.getEarning());
  }

  @Test
  void setEarning() {
    f.setEarning(300000);
    assertEquals(300000, f.getEarning());
  }

  @Test
  void getIncomeTaxPaid() {
    assertEquals(30000, f.getIncomeTaxPaid());
  }

  @Test
  void setIncomeTaxPaid() {
    f.setIncomeTaxPaid(40000);
    assertEquals(40000, f.getIncomeTaxPaid());
  }

  @Test
  void getMortgagePaid() {
    assertEquals(10000, f.getMortgagePaid());
  }

  @Test
  void setMortgagePaid() {
    f.setMortgagePaid(20000);
    assertEquals(20000, f.getMortgagePaid());
  }

  @Test
  void getPropertyTaxPaid() {
    assertEquals(25000, f.getPropertyTaxPaid());
  }

  @Test
  void setPropertyTaxPaid() {
    f.setPropertyTaxPaid(30000);
    assertEquals(30000, f.getPropertyTaxPaid());
  }

  @Test
  void getStudentLoanAndTuitionPaid() {
    assertEquals(10000, f.getStudentLoanAndTuitionPaid());
  }

  @Test
  void setStudentLoanAndTuitionPaid() {
    f.setStudentLoanAndTuitionPaid(20000);
    assertEquals(20000, f.getStudentLoanAndTuitionPaid());
  }

  @Test
  void getRetirementContribution() {
    assertEquals(30000, f.getRetirementContribution());
  }

  @Test
  void setRetirementContribution() {
    f.setRetirementContribution(40000);
    assertEquals(40000, f.getRetirementContribution());
  }

  @Test
  void getHealthContribution() {
    assertEquals(15000, f.getHealthContribution());
  }

  @Test
  void setHealthContribution() {
    f.setHealthContribution(20000);
    assertEquals(20000, f.getHealthContribution());
  }

  @Test
  void getDonate() {
    assertEquals(3000, f.getDonate());
  }

  @Test
  void setDonate() {
    f.setDonate(3500.5);
    assertEquals(3500.5, f.getDonate());
  }

  @Test
  void getNumDependent() {
    assertEquals(2, f.getNumDependents());
  }

  @Test
  void setNumDependent() {
    f.setNumDependents(3);
    assertEquals(3, f.getNumDependents());
  }

  @Test
  void getNumMinorChild() {
    assertEquals(2, f.getNumMinorChild());
  }

  @Test
  void setNumMinorChild() {
    f.setNumMinorChild(3);
    assertEquals(3, f.getNumMinorChild());
  }

  @Test
  void getChildcare() {
    assertEquals(3000, f.getChildCare());
  }

  @Test
  void setChildcare() {
    f.setChildCare(4000);
    assertEquals(4000, f.getChildCare());
  }

  @Test
  void getDependentCare() {
    assertEquals(0, f.getDependentCare());
  }

  @Test
  void setDependentCare() {
    f.setDependentCare(2000);
    assertEquals(2000, f.getDependentCare());
  }

  @Test
  void calculateTaxes() {
    assertEquals(37462.5, f.calculateTaxes());
  }

  @Test
  void calculateTaxesWithMortgageDeduction() {
    f.setEarning(200000);
    assertEquals(27750, f.calculateTaxes());
  }

  @Test
  void calculateTaxesLessThanThreshold() {
    f.setEarning(110000);
    assertEquals(8700, f.calculateTaxes());
  }

  @Test
  void calculateTaxesWithChildcare() {
    f.setChildCare(6000);
    f.setEarning(200000);
    assertEquals(27518.75, f.calculateTaxes());
  }
}