package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 173 "model.ump"
// line 253 "model.ump"
public class BankTransfer extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BankTransfer Attributes
  private String bankCode;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BankTransfer(String aPaymentID, int aAmount, String aCurrency, String aBookingID, Booking aBooking, String aBankCode)
  {
    super(aPaymentID, aAmount, aCurrency, aBookingID, aBooking);
    bankCode = aBankCode;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBankCode(String aBankCode)
  {
    boolean wasSet = false;
    bankCode = aBankCode;
    wasSet = true;
    return wasSet;
  }

  public String getBankCode()
  {
    return bankCode;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "bankCode" + ":" + getBankCode()+ "]";
  }
}