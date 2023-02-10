package problem3.IndividualFiler;

import problem3.ContactInfo;
import problem3.Filer;

/**
 * Represents an Individual Filer account. Subclass: Employee
 *
 * @author Cody Cao
 */
public abstract class IndividualFiler extends Filer {

  public static final double HEALTH_RETIREMENT_DEDUCTION_RATIO = 0.7;

  //Thresholds for final tax amount
  public static final int TAXABLE_INCOME_THRESHOLD = 55000;
  public static final double TAXABLE_INCOME_LOWER_THRESHOLD_RATIO = 0.15;
  public static final double TAXABLE_INCOME_HIGHER_THRESHOLD_RATIO = 0.19;

  /**
   * Create a new IndividualFiler object that holds information of the tax filer
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
  protected IndividualFiler(
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

  /**
   * @return the final amount of taxes the filer has to pay
   */
  @Override
  public double calculateTaxes() {
    double basicTaxableIncome = super.getBasicTaxableIncome();
    double retirementHealthDeduction =
        super.getRetirementAndHealthSavingDeduction() * HEALTH_RETIREMENT_DEDUCTION_RATIO;
    double mortgageAndPropertyDeduction = super.getMortgageAndPropertyDeduction();

    //Set the difference to 0 if the amount of health and retirement deduction is more than the amount of taxable income
    if (retirementHealthDeduction > basicTaxableIncome) {
      retirementHealthDeduction = basicTaxableIncome;
    }

    double finalTaxableAmount = basicTaxableIncome -
        retirementHealthDeduction -
        mortgageAndPropertyDeduction;

    if (finalTaxableAmount < TAXABLE_INCOME_THRESHOLD) {
      return finalTaxableAmount * TAXABLE_INCOME_LOWER_THRESHOLD_RATIO;
    }
    return finalTaxableAmount * TAXABLE_INCOME_HIGHER_THRESHOLD_RATIO;
  }
}
