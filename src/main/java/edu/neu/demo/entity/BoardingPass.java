package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.time.LocalDateTime;
import java.util.*;

// line 23 "model.ump"
// line 196 "model.ump"
public class BoardingPass
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BoardingPass Attributes
  private String passNo;
  private LocalDateTime boardingTime;
  private String gate;
  private String passengerInfo;

  //BoardingPass Associations
  private List<Passenger> passengers;
  private Flight flight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BoardingPass(String aPassNo, LocalDateTime aBoardingTime, String aGate, String aPassengerInfo, Flight aFlight)
  {
    passNo = aPassNo;
    boardingTime = aBoardingTime;
    gate = aGate;
    passengerInfo = aPassengerInfo;
    passengers = new ArrayList<Passenger>();
    boolean didAddFlight = setFlight(aFlight);
    if (!didAddFlight)
    {
      throw new RuntimeException("Unable to create boardingPass due to flight. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassNo(String aPassNo)
  {
    boolean wasSet = false;
    passNo = aPassNo;
    wasSet = true;
    return wasSet;
  }

  public boolean setBoardingTime(LocalDateTime aBoardingTime)
  {
    boolean wasSet = false;
    boardingTime = aBoardingTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setGate(String aGate)
  {
    boolean wasSet = false;
    gate = aGate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassengerInfo(String aPassengerInfo)
  {
    boolean wasSet = false;
    passengerInfo = aPassengerInfo;
    wasSet = true;
    return wasSet;
  }

  public String getPassNo()
  {
    return passNo;
  }

  public LocalDateTime getBoardingTime()
  {
    return boardingTime;
  }

  public String getGate()
  {
    return gate;
  }

  public String getPassengerInfo()
  {
    return passengerInfo;
  }
  /* Code from template association_GetMany */
  public Passenger getPassenger(int index)
  {
    Passenger aPassenger = passengers.get(index);
    return aPassenger;
  }

  public List<Passenger> getPassengers()
  {
    List<Passenger> newPassengers = Collections.unmodifiableList(passengers);
    return newPassengers;
  }

  public int numberOfPassengers()
  {
    int number = passengers.size();
    return number;
  }

  public boolean hasPassengers()
  {
    boolean has = passengers.size() > 0;
    return has;
  }

  public int indexOfPassenger(Passenger aPassenger)
  {
    int index = passengers.indexOf(aPassenger);
    return index;
  }
  /* Code from template association_GetOne */
  public Flight getFlight()
  {
    return flight;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPassengers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPassenger(Passenger aPassenger)
  {
    boolean wasAdded = false;
    if (passengers.contains(aPassenger)) { return false; }
    passengers.add(aPassenger);
    if (aPassenger.indexOfBoardingPass(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPassenger.addBoardingPass(this);
      if (!wasAdded)
      {
        passengers.remove(aPassenger);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePassenger(Passenger aPassenger)
  {
    boolean wasRemoved = false;
    if (!passengers.contains(aPassenger))
    {
      return wasRemoved;
    }

    int oldIndex = passengers.indexOf(aPassenger);
    passengers.remove(oldIndex);
    if (aPassenger.indexOfBoardingPass(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPassenger.removeBoardingPass(this);
      if (!wasRemoved)
      {
        passengers.add(oldIndex,aPassenger);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPassengerAt(Passenger aPassenger, int index)
  {  
    boolean wasAdded = false;
    if(addPassenger(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePassengerAt(Passenger aPassenger, int index)
  {
    boolean wasAdded = false;
    if(passengers.contains(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPassengerAt(aPassenger, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFlight(Flight aFlight)
  {
    boolean wasSet = false;
    if (aFlight == null)
    {
      return wasSet;
    }

    Flight existingFlight = flight;
    flight = aFlight;
    if (existingFlight != null && !existingFlight.equals(aFlight))
    {
      existingFlight.removeBoardingPass(this);
    }
    flight.addBoardingPass(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Passenger> copyOfPassengers = new ArrayList<Passenger>(passengers);
    passengers.clear();
    for(Passenger aPassenger : copyOfPassengers)
    {
      aPassenger.removeBoardingPass(this);
    }
    Flight placeholderFlight = flight;
    this.flight = null;
    if(placeholderFlight != null)
    {
      placeholderFlight.removeBoardingPass(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "passNo" + ":" + getPassNo()+ "," +
            "gate" + ":" + getGate()+ "," +
            "passengerInfo" + ":" + getPassengerInfo()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "boardingTime" + "=" + (getBoardingTime() != null ? !getBoardingTime().equals(this)  ? getBoardingTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "flight = "+(getFlight()!=null?Integer.toHexString(System.identityHashCode(getFlight())):"null");
  }
}