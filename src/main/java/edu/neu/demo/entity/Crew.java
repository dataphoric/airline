package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 94 "model.ump"
// line 228 "model.ump"
public class Crew
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Crew Attributes
  private String crewID;
  private String name;
  private String position;
  private String contactInfo;

  //Crew Associations
  private Flight flight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Crew(String aCrewID, String aName, String aPosition, String aContactInfo, Flight aFlight)
  {
    crewID = aCrewID;
    name = aName;
    position = aPosition;
    contactInfo = aContactInfo;
    boolean didAddFlight = setFlight(aFlight);
    if (!didAddFlight)
    {
      throw new RuntimeException("Unable to create crew due to flight. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCrewID(String aCrewID)
  {
    boolean wasSet = false;
    crewID = aCrewID;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPosition(String aPosition)
  {
    boolean wasSet = false;
    position = aPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setContactInfo(String aContactInfo)
  {
    boolean wasSet = false;
    contactInfo = aContactInfo;
    wasSet = true;
    return wasSet;
  }

  public String getCrewID()
  {
    return crewID;
  }

  public String getName()
  {
    return name;
  }

  public String getPosition()
  {
    return position;
  }

  public String getContactInfo()
  {
    return contactInfo;
  }
  /* Code from template association_GetOne */
  public Flight getFlight()
  {
    return flight;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setFlight(Flight aFlight)
  {
    boolean wasSet = false;
    //Must provide flight to crew
    if (aFlight == null)
    {
      return wasSet;
    }

    if (flight != null && flight.numberOfCrews() <= Flight.minimumNumberOfCrews())
    {
      return wasSet;
    }

    Flight existingFlight = flight;
    flight = aFlight;
    if (existingFlight != null && !existingFlight.equals(aFlight))
    {
      boolean didRemove = existingFlight.removeCrew(this);
      if (!didRemove)
      {
        flight = existingFlight;
        return wasSet;
      }
    }
    flight.addCrew(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Flight placeholderFlight = flight;
    this.flight = null;
    if(placeholderFlight != null)
    {
      placeholderFlight.removeCrew(this);
    }
  }

  // line 102 "model.ump"
   public void viewFlight(){
    
  }

  // line 105 "model.ump"
   public void performSafetCheck(){
    
  }

  // line 108 "model.ump"
   public void assistPassengers(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "crewID" + ":" + getCrewID()+ "," +
            "name" + ":" + getName()+ "," +
            "position" + ":" + getPosition()+ "," +
            "contactInfo" + ":" + getContactInfo()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "flight = "+(getFlight()!=null?Integer.toHexString(System.identityHashCode(getFlight())):"null");
  }
}