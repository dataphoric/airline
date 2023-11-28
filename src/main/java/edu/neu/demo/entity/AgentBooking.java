package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 141 "model.ump"
// line 248 "model.ump"
public class AgentBooking extends Booking
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AgentBooking Attributes
  private String agentID;
  private String agentName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AgentBooking(String aBookingID, Date aBookingDate, String aPaymentInfo, Passenger aPassenger, Seat aSeat, Payment aPayment, String aAgentID, String aAgentName)
  {
    super(aBookingID, aBookingDate, aPaymentInfo, aPassenger, aSeat, aPayment);
    agentID = aAgentID;
    agentName = aAgentName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAgentID(String aAgentID)
  {
    boolean wasSet = false;
    agentID = aAgentID;
    wasSet = true;
    return wasSet;
  }

  public boolean setAgentName(String aAgentName)
  {
    boolean wasSet = false;
    agentName = aAgentName;
    wasSet = true;
    return wasSet;
  }

  public String getAgentID()
  {
    return agentID;
  }

  public String getAgentName()
  {
    return agentName;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "agentID" + ":" + getAgentID()+ "," +
            "agentName" + ":" + getAgentName()+ "]";
  }
}