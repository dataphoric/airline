package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.time.LocalDateTime;
import java.util.*;

// line 65 "model.ump"
// line 217 "model.ump"
public class Flight
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Flight Attributes
  private String flightNumber;
  private String departureAirport;
  private String arrivalAirport;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private String stops;

  //Flight Associations
  private List<Seat> seats;
  private Aircraft aircraft;
  private List<Crew> crews;
  private List<BoardingPass> boardingPasses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Flight(String aFlightNumber, String aDepartureAirport, String aArrivalAirport, LocalDateTime aDepartureTime, LocalDateTime aArrivalTime, String aStops, Aircraft aAircraft)
  {
    flightNumber = aFlightNumber;
    departureAirport = aDepartureAirport;
    arrivalAirport = aArrivalAirport;
    departureTime = aDepartureTime;
    arrivalTime = aArrivalTime;
    stops = aStops;
    seats = new ArrayList<Seat>();
    if (aAircraft == null || aAircraft.getFlight() != null)
    {
      throw new RuntimeException("Unable to create Flight due to aAircraft. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    aircraft = aAircraft;
    crews = new ArrayList<Crew>();
    boardingPasses = new ArrayList<BoardingPass>();
  }

  public Flight(String aFlightNumber, String aDepartureAirport, String aArrivalAirport, LocalDateTime aDepartureTime, LocalDateTime aArrivalTime, String aStops, String aAircraftIDForAircraft, String aModelForAircraft, int aCapacityForAircraft)
  {
    flightNumber = aFlightNumber;
    departureAirport = aDepartureAirport;
    arrivalAirport = aArrivalAirport;
    departureTime = aDepartureTime;
    arrivalTime = aArrivalTime;
    stops = aStops;
    seats = new ArrayList<Seat>();
    aircraft = new Aircraft(aAircraftIDForAircraft, aModelForAircraft, aCapacityForAircraft, this);
    crews = new ArrayList<Crew>();
    boardingPasses = new ArrayList<BoardingPass>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFlightNumber(String aFlightNumber)
  {
    boolean wasSet = false;
    flightNumber = aFlightNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setDepartureAirport(String aDepartureAirport)
  {
    boolean wasSet = false;
    departureAirport = aDepartureAirport;
    wasSet = true;
    return wasSet;
  }

  public boolean setArrivalAirport(String aArrivalAirport)
  {
    boolean wasSet = false;
    arrivalAirport = aArrivalAirport;
    wasSet = true;
    return wasSet;
  }

  public boolean setDepartureTime(LocalDateTime aDepartureTime)
  {
    boolean wasSet = false;
    departureTime = aDepartureTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setArrivalTime(LocalDateTime aArrivalTime)
  {
    boolean wasSet = false;
    arrivalTime = aArrivalTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setStops(String aStops)
  {
    boolean wasSet = false;
    stops = aStops;
    wasSet = true;
    return wasSet;
  }

  public String getFlightNumber()
  {
    return flightNumber;
  }

  public String getDepartureAirport()
  {
    return departureAirport;
  }

  public String getArrivalAirport()
  {
    return arrivalAirport;
  }

  public LocalDateTime getDepartureTime()
  {
    return departureTime;
  }

  public LocalDateTime getArrivalTime()
  {
    return arrivalTime;
  }

  public String getStops()
  {
    return stops;
  }
  /* Code from template association_GetMany */
  public Seat getSeat(int index)
  {
    Seat aSeat = seats.get(index);
    return aSeat;
  }

  public List<Seat> getSeats()
  {
    List<Seat> newSeats = Collections.unmodifiableList(seats);
    return newSeats;
  }

  public int numberOfSeats()
  {
    int number = seats.size();
    return number;
  }

  public boolean hasSeats()
  {
    boolean has = seats.size() > 0;
    return has;
  }

  public int indexOfSeat(Seat aSeat)
  {
    int index = seats.indexOf(aSeat);
    return index;
  }
  /* Code from template association_GetOne */
  public Aircraft getAircraft()
  {
    return aircraft;
  }
  /* Code from template association_GetMany */
  public Crew getCrew(int index)
  {
    Crew aCrew = crews.get(index);
    return aCrew;
  }

  public List<Crew> getCrews()
  {
    List<Crew> newCrews = Collections.unmodifiableList(crews);
    return newCrews;
  }

  public int numberOfCrews()
  {
    int number = crews.size();
    return number;
  }

  public boolean hasCrews()
  {
    boolean has = crews.size() > 0;
    return has;
  }

  public int indexOfCrew(Crew aCrew)
  {
    int index = crews.indexOf(aCrew);
    return index;
  }
  /* Code from template association_GetMany */
  public BoardingPass getBoardingPass(int index)
  {
    BoardingPass aBoardingPass = boardingPasses.get(index);
    return aBoardingPass;
  }

  public List<BoardingPass> getBoardingPasses()
  {
    List<BoardingPass> newBoardingPasses = Collections.unmodifiableList(boardingPasses);
    return newBoardingPasses;
  }

  public int numberOfBoardingPasses()
  {
    int number = boardingPasses.size();
    return number;
  }

  public boolean hasBoardingPasses()
  {
    boolean has = boardingPasses.size() > 0;
    return has;
  }

  public int indexOfBoardingPass(BoardingPass aBoardingPass)
  {
    int index = boardingPasses.indexOf(aBoardingPass);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSeats()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Seat addSeat(String aSeatNumber, String aSeatClass, boolean aIsOccuped, Booking aBooking)
  {
    return new Seat(aSeatNumber, aSeatClass, aIsOccuped, this, aBooking);
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seats.contains(aSeat)) { return false; }
    Flight existingFlight = aSeat.getFlight();
    boolean isNewFlight = existingFlight != null && !this.equals(existingFlight);
    if (isNewFlight)
    {
      aSeat.setFlight(this);
    }
    else
    {
      seats.add(aSeat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeat, as it must always have a flight
    if (!this.equals(aSeat.getFlight()))
    {
      seats.remove(aSeat);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSeatAt(Seat aSeat, int index)
  {  
    boolean wasAdded = false;
    if(addSeat(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatAt(Seat aSeat, int index)
  {
    boolean wasAdded = false;
    if(seats.contains(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatAt(aSeat, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfCrewsValid()
  {
    boolean isValid = numberOfCrews() >= minimumNumberOfCrews();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCrews()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Crew addCrew(String aCrewID, String aName, String aPosition, String aContactInfo)
  {
    Crew aNewCrew = new Crew(aCrewID, aName, aPosition, aContactInfo, this);
    return aNewCrew;
  }

  public boolean addCrew(Crew aCrew)
  {
    boolean wasAdded = false;
    if (crews.contains(aCrew)) { return false; }
    Flight existingFlight = aCrew.getFlight();
    boolean isNewFlight = existingFlight != null && !this.equals(existingFlight);

    if (isNewFlight && existingFlight.numberOfCrews() <= minimumNumberOfCrews())
    {
      return wasAdded;
    }
    if (isNewFlight)
    {
      aCrew.setFlight(this);
    }
    else
    {
      crews.add(aCrew);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCrew(Crew aCrew)
  {
    boolean wasRemoved = false;
    //Unable to remove aCrew, as it must always have a flight
    if (this.equals(aCrew.getFlight()))
    {
      return wasRemoved;
    }

    //flight already at minimum (1)
    if (numberOfCrews() <= minimumNumberOfCrews())
    {
      return wasRemoved;
    }

    crews.remove(aCrew);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCrewAt(Crew aCrew, int index)
  {  
    boolean wasAdded = false;
    if(addCrew(aCrew))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCrews()) { index = numberOfCrews() - 1; }
      crews.remove(aCrew);
      crews.add(index, aCrew);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCrewAt(Crew aCrew, int index)
  {
    boolean wasAdded = false;
    if(crews.contains(aCrew))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCrews()) { index = numberOfCrews() - 1; }
      crews.remove(aCrew);
      crews.add(index, aCrew);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCrewAt(aCrew, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBoardingPasses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BoardingPass addBoardingPass(String aPassNo, LocalDateTime aBoardingTime, String aGate, String aPassengerInfo)
  {
    return new BoardingPass(aPassNo, aBoardingTime, aGate, aPassengerInfo, this);
  }

  public boolean addBoardingPass(BoardingPass aBoardingPass)
  {
    boolean wasAdded = false;
    if (boardingPasses.contains(aBoardingPass)) { return false; }
    Flight existingFlight = aBoardingPass.getFlight();
    boolean isNewFlight = existingFlight != null && !this.equals(existingFlight);
    if (isNewFlight)
    {
      aBoardingPass.setFlight(this);
    }
    else
    {
      boardingPasses.add(aBoardingPass);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBoardingPass(BoardingPass aBoardingPass)
  {
    boolean wasRemoved = false;
    //Unable to remove aBoardingPass, as it must always have a flight
    if (!this.equals(aBoardingPass.getFlight()))
    {
      boardingPasses.remove(aBoardingPass);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBoardingPassAt(BoardingPass aBoardingPass, int index)
  {  
    boolean wasAdded = false;
    if(addBoardingPass(aBoardingPass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBoardingPasses()) { index = numberOfBoardingPasses() - 1; }
      boardingPasses.remove(aBoardingPass);
      boardingPasses.add(index, aBoardingPass);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBoardingPassAt(BoardingPass aBoardingPass, int index)
  {
    boolean wasAdded = false;
    if(boardingPasses.contains(aBoardingPass))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBoardingPasses()) { index = numberOfBoardingPasses() - 1; }
      boardingPasses.remove(aBoardingPass);
      boardingPasses.add(index, aBoardingPass);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBoardingPassAt(aBoardingPass, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (seats.size() > 0)
    {
      Seat aSeat = seats.get(seats.size() - 1);
      aSeat.delete();
      seats.remove(aSeat);
    }
    
    Aircraft existingAircraft = aircraft;
    aircraft = null;
    if (existingAircraft != null)
    {
      existingAircraft.delete();
    }
    for(int i=crews.size(); i > 0; i--)
    {
      Crew aCrew = crews.get(i - 1);
      aCrew.delete();
    }
    for(int i=boardingPasses.size(); i > 0; i--)
    {
      BoardingPass aBoardingPass = boardingPasses.get(i - 1);
      aBoardingPass.delete();
    }
  }

  // line 75 "model.ump"
   public void checkAvailability(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "flightNumber" + ":" + getFlightNumber()+ "," +
            "departureAirport" + ":" + getDepartureAirport()+ "," +
            "arrivalAirport" + ":" + getArrivalAirport()+ "," +
            "stops" + ":" + getStops()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "departureTime" + "=" + (getDepartureTime() != null ? !getDepartureTime().equals(this)  ? getDepartureTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "arrivalTime" + "=" + (getArrivalTime() != null ? !getArrivalTime().equals(this)  ? getArrivalTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "aircraft = "+(getAircraft()!=null?Integer.toHexString(System.identityHashCode(getAircraft())):"null");
  }
}