package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 32 "model.ump"
// line 202 "model.ump"
public class Booking
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Booking Attributes
  private String bookingID;
  private Date bookingDate;
  private String paymentInfo;

  //Booking Associations
  private Passenger passenger;
  private Seat seat;
  private Payment payment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Booking(String aBookingID, Date aBookingDate, String aPaymentInfo, Passenger aPassenger, Seat aSeat, Payment aPayment)
  {
    bookingID = aBookingID;
    bookingDate = aBookingDate;
    paymentInfo = aPaymentInfo;
    boolean didAddPassenger = setPassenger(aPassenger);
    if (!didAddPassenger)
    {
      throw new RuntimeException("Unable to create booking due to passenger. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aSeat == null || aSeat.getBooking() != null)
    {
      throw new RuntimeException("Unable to create Booking due to aSeat. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    seat = aSeat;
    if (aPayment == null || aPayment.getBooking() != null)
    {
      throw new RuntimeException("Unable to create Booking due to aPayment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    payment = aPayment;
  }

  public Booking(String aBookingID, Date aBookingDate, String aPaymentInfo, Passenger aPassenger, String aSeatNumberForSeat, String aSeatClassForSeat, boolean aIsOccupedForSeat, Flight aFlightForSeat, String aPaymentIDForPayment, int aAmountForPayment, String aCurrencyForPayment, String aBookingIDForPayment)
  {
    bookingID = aBookingID;
    bookingDate = aBookingDate;
    paymentInfo = aPaymentInfo;
    boolean didAddPassenger = setPassenger(aPassenger);
    if (!didAddPassenger)
    {
      throw new RuntimeException("Unable to create booking due to passenger. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    seat = new Seat(aSeatNumberForSeat, aSeatClassForSeat, aIsOccupedForSeat, aFlightForSeat, this);
    payment = new Payment(aPaymentIDForPayment, aAmountForPayment, aCurrencyForPayment, aBookingIDForPayment, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBookingID(String aBookingID)
  {
    boolean wasSet = false;
    bookingID = aBookingID;
    wasSet = true;
    return wasSet;
  }

  public boolean setBookingDate(Date aBookingDate)
  {
    boolean wasSet = false;
    bookingDate = aBookingDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPaymentInfo(String aPaymentInfo)
  {
    boolean wasSet = false;
    paymentInfo = aPaymentInfo;
    wasSet = true;
    return wasSet;
  }

  public String getBookingID()
  {
    return bookingID;
  }

  public Date getBookingDate()
  {
    return bookingDate;
  }

  public String getPaymentInfo()
  {
    return paymentInfo;
  }
  /* Code from template association_GetOne */
  public Passenger getPassenger()
  {
    return passenger;
  }
  /* Code from template association_GetOne */
  public Seat getSeat()
  {
    return seat;
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
      existingPassenger.removeBooking(this);
    }
    passenger.addBooking(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Passenger placeholderPassenger = passenger;
    this.passenger = null;
    if(placeholderPassenger != null)
    {
      placeholderPassenger.removeBooking(this);
    }
    Seat existingSeat = seat;
    seat = null;
    if (existingSeat != null)
    {
      existingSeat.delete();
    }
    Payment existingPayment = payment;
    payment = null;
    if (existingPayment != null)
    {
      existingPayment.delete();
    }
  }

  // line 39 "model.ump"
   public void makePayment(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "bookingID" + ":" + getBookingID()+ "," +
            "paymentInfo" + ":" + getPaymentInfo()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bookingDate" + "=" + (getBookingDate() != null ? !getBookingDate().equals(this)  ? getBookingDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "passenger = "+(getPassenger()!=null?Integer.toHexString(System.identityHashCode(getPassenger())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "seat = "+(getSeat()!=null?Integer.toHexString(System.identityHashCode(getSeat())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "payment = "+(getPayment()!=null?Integer.toHexString(System.identityHashCode(getPayment())):"null");
  }
}