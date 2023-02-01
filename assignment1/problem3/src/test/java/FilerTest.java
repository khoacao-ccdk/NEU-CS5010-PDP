import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilerTest {

  Filer f;
  ContactInfo c;
  Name n;

  @BeforeEach
  void setUp() {
    n = new Name("Cody", "Cao");
    c = new ContactInfo(n, "225 Terry Ave", "1234567890", "test@gmail.com");
    f = new Employee(
        "12345",
        c,
        250000,
        30000,
        10000,
        25000,
        10000,
        30000,
        15000,
        3000
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
  void getBasicTaxableIncome() {
    assertEquals(220000, f.getBasicTaxableIncome());
  }

  @Test
  void getRetirementAndHealthSavingDeduction() {
    assertEquals(45000, f.getRetirementAndHealthSavingDeduction());
  }

  @Test
  void getMortgageAndPropertyDeduction() {
    assertEquals(0.0, f.getMortgageAndPropertyDeduction());
  }

  @Test
  void getMortgageAndPropertyDeductionWithDeduction() {
    f.setEarning(200000);
    assertEquals(2500, f.getMortgageAndPropertyDeduction());
  }
}