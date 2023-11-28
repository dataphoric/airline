package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 166 "model.ump"
// line 243 "model.ump"
public class Refund
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Refund Attributes
  private String refundID;
  private int refundAmt;
  private Date date;

  //Refund Associations
  private Passenger passenger;
  private Payment payment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Refund(String aRefundID, int aRefundAmt, Date aDate, Passenger aPassenger, Payment aPayment)
  {
    refundID = aRefundID;
    refundAmt = aRefundAmt;
    date = aDate;
    boolean didAddPassenger = setPassenger(aPassenger);
    if (!didAddPassenger)
    {
      throw new RuntimeException("Unable to create refund due to passenger. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddPayment = setPayment(aPayment);
    if (!didAddPayment)
    {
      throw new RuntimeException("Unable to create refund due to payment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRefundID(String aRefundID)
  {
    boolean wasSet = false;
    refundID = aRefundID;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundAmt(int aRefundAmt)
  {
    boolean wasSet = false;
    refundAmt = aRefundAmt;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getRefundID()
  {
    return refundID;
  }

  public int getRefundAmt()
  {
    return refundAmt;
  }

  public Date getDate()
  {
    return date;
  }
  /* Code from template association_GetOne */
  public Passenger getPassenger()
  {
    return passenger;
  }
  /* Code from template association_GetOne */
  public Payment getPayment()
  {
    return payment;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPassenger(Passenger aPassenger)
  {
    boolean wasSet = false;
    if (aPassenger == null)
    {
      return wasSet;
    }

    Passenger existingPassenger = passenger;
    passenger = aPassenger;
    if (existingPassenger != null && !existingPassenger.equals(aPassenger))
    {
      existingPassenger.removeRefund(this);
    }
    passenger.addRefund(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setPayment(Payment aNewPayment)
  {
    boolean wasSet = false;
    if (aNewPayment == null)
    {
      //Unable to setPayment to null, as refund must always be associated to a payment
      return wasSet;
    }
    
    Refund existingRefund = aNewPayment.getRefund();
    if (existingRefund != null && !equals(existingRefund))
    {
      //Unable to setPayment, the current payment already has a refund, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Payment anOldPayment = payment;
    payment = aNewPayment;
    payment.setRefund(this);

    if (anOldPayment != null)
    {
      anOldPayment.setRefund(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Passenger placeholderPassenger = passenger;
    this.passenger = null;
    if(placeholderPassenger != null)
    {
      placeholderPassenger.removeRefund(this);
    }
    Payment existingPayment = payment;
    payment = null;
    if (existingPayment != null)
    {
      existingPayment.setRefund(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "refundID" + ":" + getRefundID()+ "," +
            "refundAmt" + ":" + getRefundAmt()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "passenger = "+(getPassenger()!=null?Integer.toHexString(System.identityHashCode(getPassenger())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "payment = "+(getPayment()!=null?Integer.toHexString(System.identityHashCode(getPayment())):"null");
  }
}