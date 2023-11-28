package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 78 "model.ump"
// line 222 "model.ump"
public class Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Payment Attributes
  private String paymentID;
  private int amount;
  private String currency;
  private String bookingID;

  //Payment Associations
  private Refund refund;
  private Booking booking;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Payment(String aPaymentID, int aAmount, String aCurrency, String aBookingID, Booking aBooking)
  {
    paymentID = aPaymentID;
    amount = aAmount;
    currency = aCurrency;
    bookingID = aBookingID;
    if (aBooking == null || aBooking.getPayment() != null)
    {
      throw new RuntimeException("Unable to create Payment due to aBooking. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    booking = aBooking;
  }

  public Payment(String aPaymentID, int aAmount, String aCurrency, String aBookingID, String aBookingIDForBooking, Date aBookingDateForBooking, String aPaymentInfoForBooking, Passenger aPassengerForBooking, Seat aSeatForBooking)
  {
    paymentID = aPaymentID;
    amount = aAmount;
    currency = aCurrency;
    bookingID = aBookingID;
    booking = new Booking(aBookingIDForBooking, aBookingDateForBooking, aPaymentInfoForBooking, aPassengerForBooking, aSeatForBooking, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPaymentID(String aPaymentID)
  {
    boolean wasSet = false;
    paymentID = aPaymentID;
    wasSet = true;
    return wasSet;
  }

  public boolean setAmount(int aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrency(String aCurrency)
  {
    boolean wasSet = false;
    currency = aCurrency;
    wasSet = true;
    return wasSet;
  }

  public boolean setBookingID(String aBookingID)
  {
    boolean wasSet = false;
    bookingID = aBookingID;
    wasSet = true;
    return wasSet;
  }

  public String getPaymentID()
  {
    return paymentID;
  }

  public int getAmount()
  {
    return amount;
  }

  public String getCurrency()
  {
    return currency;
  }

  public String getBookingID()
  {
    return bookingID;
  }
  /* Code from template association_GetOne */
  public Refund getRefund()
  {
    return refund;
  }

  public boolean hasRefund()
  {
    boolean has = refund != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Booking getBooking()
  {
    return booking;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setRefund(Refund aNewRefund)
  {
    boolean wasSet = false;
    if (refund != null && !refund.equals(aNewRefund) && equals(refund.getPayment()))
    {
      //Unable to setRefund, as existing refund would become an orphan
      return wasSet;
    }

    refund = aNewRefund;
    Payment anOldPayment = aNewRefund != null ? aNewRefund.getPayment() : null;

    if (!this.equals(anOldPayment))
    {
      if (anOldPayment != null)
      {
        anOldPayment.refund = null;
      }
      if (refund != null)
      {
        refund.setPayment(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Refund existingRefund = refund;
    refund = null;
    if (existingRefund != null)
    {
      existingRefund.delete();
    }
    Booking existingBooking = booking;
    booking = null;
    if (existingBooking != null)
    {
      existingBooking.delete();
    }
  }

  // line 87 "model.ump"
   public void makeRefund(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "paymentID" + ":" + getPaymentID()+ "," +
            "amount" + ":" + getAmount()+ "," +
            "currency" + ":" + getCurrency()+ "," +
            "bookingID" + ":" + getBookingID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "refund = "+(getRefund()!=null?Integer.toHexString(System.identityHashCode(getRefund())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "booking = "+(getBooking()!=null?Integer.toHexString(System.identityHashCode(getBooking())):"null");
  }
}