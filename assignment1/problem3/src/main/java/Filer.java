/**
 * Represents a tax filer's account information
 *
 * @author Cody Cao
 */
public abstract class Filer {

  //Thresholds for mortgage interest and property tax deduction
  public static final int MAX_EARNING_FOR_MORTGAGE_PROPERTY_DEDUCTION = 250000;
  public static final int MIN_MORTGAGE_PROPERTY_PAID_FOR_MORTGAGE_PROPERTY_DEDUCTION = 12500;
  public static final int MORTGAGE_PROPERTY_DEDUCTION_AMOUNT = 2500;

  private String id;
  private ContactInfo contact;
  private double earning;
  private double incomeTaxPaid;
  private double mortgagePaid;
  private double propertyTaxPaid;
  private double studentLoanAndTuitionPaid;
  private double retirementContribution;
  private double healthContribution;
  private double donate;

  /**
   * Create a new Filer object that holds information of the tax filer
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
  protected Filer(
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
    this.id = id;
    this.contact = contact;
    this.earning = earning;
    this.incomeTaxPaid = incomeTaxPaid;
    this.mortgagePaid = mortgagePaid;
    this.propertyTaxPaid = propertyTaxPaid;
    this.studentLoanAndTuitionPaid = studentLoanAndTuitionPaid;
    this.retirementContribution = retirementContribution;
    this.healthContribution = healthContribution;
    this.donate = donate;
  }


  /**
   * @return filer's id
   */
  public String getId() {
    return id;
  }

  /**
   * @return a ContactInfo object that holds filer's contact information
   */
  public ContactInfo getContact() {
    return contact;
  }

  /**
   * Set filer's contact information
   *
   * @param contact a ContactInfo object that holds filer's contact information
   */
  public void setContact(ContactInfo contact) {
    this.contact = contact;
  }

  /**
   * @return filer's last year earning
   */
  public double getEarning() {
    return earning;
  }

  /**
   * Set filer's last year earning
   *
   * @param earning filer's last year earning
   */
  public void setEarning(double earning) {
    this.earning = earning;
  }

  /**
   * @return amount of income tax filer has paid
   */
  public double getIncomeTaxPaid() {
    return incomeTaxPaid;
  }

  /**
   * Set amount of income tax filer has paid
   *
   * @param incomeTaxPaid amount of income tax filer has paid
   */
  public void setIncomeTaxPaid(double incomeTaxPaid) {
    this.incomeTaxPaid = incomeTaxPaid;
  }

  /**
   * @return amount of mortgage interest filer has paid
   */
  public double getMortgagePaid() {
    return mortgagePaid;
  }

  /**
   * Set amount of mortgage interest filer has paid
   *
   * @param mortgagePaid amount of mortgage interest filer has paid
   */
  public void setMortgagePaid(double mortgagePaid) {
    this.mortgagePaid = mortgagePaid;
  }

  /**
   * @return amount of property tax filer has paid
   */
  public double getPropertyTaxPaid() {
    return propertyTaxPaid;
  }

  /**
   * Set amount of property tax filer has paid
   *
   * @param propertyTaxPaid amount of property tax filer has paid
   */
  public void setPropertyTaxPaid(double propertyTaxPaid) {
    this.propertyTaxPaid = propertyTaxPaid;
  }

  /**
   * @return amount of student loan and tuition filer has paid
   */
  public double getStudentLoanAndTuitionPaid() {
    return studentLoanAndTuitionPaid;
  }

  /**
   * Set  amount of student loan and tuition filer has paid
   *
   * @param studentLoanAndTuitionPaid amount of student loan and tuition filer has paid
   */
  public void setStudentLoanAndTuitionPaid(double studentLoanAndTuitionPaid) {
    this.studentLoanAndTuitionPaid = studentLoanAndTuitionPaid;
  }

  /**
   * @return amount the filer has contributed to their retirement savings account
   */
  public double getRetirementContribution() {
    return retirementContribution;
  }

  /**
   * Set the amount the filer has contributed to their retirement savings account
   *
   * @param retirementContribution amount the filer has contributed to their retirement savings
   *                               account
   */
  public void setRetirementContribution(double retirementContribution) {
    this.retirementContribution = retirementContribution;
  }

  /**
   * @return amount the filer has contributed to their health savings account
   */
  public double getHealthContribution() {
    return healthContribution;
  }

  /**
   * Set the amount the filer has contributed to their health savings account
   *
   * @param healthContribution amount the filer has contributed to their health savings account
   */
  public void setHealthContribution(double healthContribution) {
    this.healthContribution = healthContribution;
  }

  /**
   * @return amount of charitable donations and contributions the filer has made
   */
  public double getDonate() {
    return donate;
  }

  /**
   * Set the amount of charitable donations and contributions the filer has made
   *
   * @param donate amount of charitable donations and contributions the filer has made
   */
  public void setDonate(double donate) {
    this.donate = donate;
  }

  /**
   * @return the final amount of taxes the filer has to pay
   */
  public abstract double calculateTaxes();

  /**
   * @return filer's basic taxable income
   */
  protected double getBasicTaxableIncome() {
    return earning - incomeTaxPaid;
  }

  /**
   * @return the amount of taxable income being deducted when factoring retirement saving and
   * healthcare savings
   */
  protected double getRetirementAndHealthSavingDeduction() {
    return retirementContribution + healthContribution;
  }

  /**
   * @return the amount of taxable income being deducted when factoring the amount of mortgage
   * interest and property taxes
   */
  protected double getMortgageAndPropertyDeduction() {
    double mortgageAndProperty = mortgagePaid + propertyTaxPaid;
    if (earning < MAX_EARNING_FOR_MORTGAGE_PROPERTY_DEDUCTION
        && mortgageAndProperty > MIN_MORTGAGE_PROPERTY_PAID_FOR_MORTGAGE_PROPERTY_DEDUCTION) {
      return MORTGAGE_PROPERTY_DEDUCTION_AMOUNT;
    }
    return 0;
  }


}
