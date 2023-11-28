package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 148 "model.ump"
// line 233 "model.ump"
public class DirectBooking extends Booking
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //DirectBooking Attributes
  private String status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public DirectBooking(String aBookingID, Date aBookingDate, String aPaymentInfo, Passenger aPassenger, Seat aSeat, Payment aPayment, String aStatus)
  {
    super(aBookingID, aBookingDate, aPaymentInfo, aPassenger, aSeat, aPayment);
    status = aStatus;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(String aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public String getStatus()
  {
    return status;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "status" + ":" + getStatus()+ "]";
  }
}