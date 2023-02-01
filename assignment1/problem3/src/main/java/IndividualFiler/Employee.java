/**
 * Represents an employee account filing their personal taxes.
 *
 * @author Cody Cao
 */
public class Employee extends IndividualFiler {

  /**
   * Create a new Employee object that holds information of the employee filer
   *
   * @param id                        a String represents a filer's unique identifier
   * @param contact                   a ContactInfo object holds filer's contact information
   * @param earning                   a double represent filer's last year earnings
   * @param incomeTaxPaid             a double represents the amount of tax the filer already paid
   * @param mortgagePaid              a double represents the amount of mortgage interest the filer
   *                                  paid
   * @param propertyTaxPaid           a double represents the amount of property tax the filer paid
   * @param studentLoanAndTuitionPaid a double represents the amount of student loan and tuition the
   *                                  filer paid
   * @param retirementContribution    a double represents the amount the filer has made to their
   *                                  retirement savings account
   * @param healthContribution        a double represents the amount the filer has made to their
   *                                  health savings account
   * @param donate                    a double represents the amount of charitable donations and
   *                                  contributions the filer has made
   */
  public Employee(
      String id,
      ContactInfo contact,
      double earning,
      double incomeTaxPaid,
      double mortgagePaid,
      double propertyTaxPaid,
      double studentLoanAndTuitionPaid,
      double retirementContribution,
      double healthContribution,
      double donate) {
    super(id,
        contact,
        earning,
        incomeTaxPaid,
        mortgagePaid,
        propertyTaxPaid,
        studentLoanAndTuitionPaid,
        retirementContribution,
        healthContribution,
        donate);
  }
}
