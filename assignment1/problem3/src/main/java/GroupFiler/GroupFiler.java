/**
 * Represents a Group Filer account
 *
 * @author Cody Cao
 */
public abstract class GroupFiler extends Filer {

  //Thresholds for childcare deduction
  public static final int MAX_INCOME_FOR_CHILD_DEDUCTION = 250000;
  public static final int MIN_EXPENSES_FOR_CHILD_DEDUCTION = 5000;
  public static final int CHILD_DEDUCTION_AMOUNT = 1250;

  //Thresholds for healthcare and retirement savings deduction
  public static final double HEALTH_RETIREMENT_DEDUCTION_RATIO = 0.65;
  public static final double MAX_HEALTH_RETIREMENT_DEDUCTION = 17500;

  //Thresholds for final tax amount
  public static final int TAXABLE_INCOME_THRESHOLD = 90000;
  public static final double TAXABLE_INCOME_LOWER_THRESHOLD_RATIO = 0.145;
  public static final double TAXABLE_INCOME_HIGHER_THRESHOLD_RATIO = 0.185;
  private int numDependents;
  private int numMinorChild;
  private double childCare;
  private double dependentCare;

  /**
   * Create a new Group Filer object that holds information of the tax filer
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
   * @param numDependents             an int represents number of dependents the filer has
   * @param numMinorChild             an int represents number of minor children the filer has
   * @param childCare                 a double represents the amount the filer has paid for
   *                                  childcare expenses
   * @param dependentCare             a double represents the amount the filer has paid for
   *                                  dependent care expenses
   */
  protected GroupFiler(
      String id,
      ContactInfo contact,
      double earning,
      double incomeTaxPaid,
      double mortgagePaid,
      double propertyTaxPaid,
      double studentLoanAndTuitionPaid,
      double retirementContribution,
      double healthContribution,
      double donate,
      int numDependents,
      int numMinorChild,
      double childCare,
      double dependentCare) {
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
    this.numDependents = numDependents;
    this.numMinorChild = numMinorChild;
    this.childCare = childCare;
    this.dependentCare = dependentCare;
  }

  /**
   *
   * @return number of dependent
   */
  public int getNumDependents() {
    return numDependents;
  }

  /**
   * Set filer's number of dependent
   * @param numDependents number of dependent
   */
  public void setNumDependents(int numDependents) {
    this.numDependents = numDependents;
  }

  /**
   *
   * @return number of minor child
   */
  public int getNumMinorChild() {
    return numMinorChild;
  }

  /**
   * Set filer's number of minor child
   * @param numMinorChild
   */
  public void setNumMinorChild(int numMinorChild) {
    this.numMinorChild = numMinorChild;
  }

  /**
   *
   * @return the amount the filer has paid for childcare
   */
  public double getChildCare() {
    return childCare;
  }

  /**
   * Set the amount the filer has paid for childcare
   * @param childCare the amount the filer has paid for childcare
   */
  public void setChildCare(double childCare) {
    this.childCare = childCare;
  }

  /**
   *
   * @return the amount the filer has paid for dependent care
   */
  public double getDependentCare() {
    return dependentCare;
  }

  /**
   * Set the amount the filer has paid for dependent care
   * @param dependentCare the amount the filer has paid for dependent care
   */
  public void setDependentCare(double dependentCare) {
    this.dependentCare = dependentCare;
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
    double childcareDeduction = getChildcareDeduction();

    //Floor tax deduction for retirement and healthcare savings to the floor value
    if (retirementHealthDeduction > MAX_HEALTH_RETIREMENT_DEDUCTION) {
      retirementHealthDeduction = MAX_HEALTH_RETIREMENT_DEDUCTION;
    }

    //Set the difference to 0 if the amount of health and retirement deduction is more than the amount of taxable income
    if (retirementHealthDeduction > basicTaxableIncome) {
      retirementHealthDeduction = basicTaxableIncome;
    }

    double finalTaxableAmount = basicTaxableIncome -
        retirementHealthDeduction -
        mortgageAndPropertyDeduction -
        childcareDeduction;

    if (finalTaxableAmount < TAXABLE_INCOME_THRESHOLD) {
      return finalTaxableAmount * TAXABLE_INCOME_LOWER_THRESHOLD_RATIO;
    }
    return finalTaxableAmount * TAXABLE_INCOME_HIGHER_THRESHOLD_RATIO;
  }

  /**
   * @return the amount of taxable income deduction when factoring the childcare expenses
   */
  private double getChildcareDeduction() {
    if (super.getBasicTaxableIncome() < MAX_INCOME_FOR_CHILD_DEDUCTION
        && childCare > MIN_EXPENSES_FOR_CHILD_DEDUCTION) {
      return CHILD_DEDUCTION_AMOUNT;
    }
    return 0;
  }
}
