package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 179 "model.ump"
// line 258 "model.ump"
public class CardPayment extends Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CardPayment Attributes
  private String cardType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CardPayment(String aPaymentID, int aAmount, String aCurrency, String aBookingID, Booking aBooking, String aCardType)
  {
    super(aPaymentID, aAmount, aCurrency, aBookingID, aBooking);
    cardType = aCardType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCardType(String aCardType)
  {
    boolean wasSet = false;
    cardType = aCardType;
    wasSet = true;
    return wasSet;
  }

  public String getCardType()
  {
    return cardType;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "cardType" + ":" + getCardType()+ "]";
  }
}