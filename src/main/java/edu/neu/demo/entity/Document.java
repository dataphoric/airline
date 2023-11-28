package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 154 "model.ump"
// line 238 "model.ump"
public class Document
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Document Attributes
  private String docID;
  private String docType;

  //Document Associations
  private Passenger passenger;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Document(String aDocID, String aDocType, Passenger aPassenger)
  {
    docID = aDocID;
    docType = aDocType;
    if (aPassenger == null || aPassenger.getDocument() != null)
    {
      throw new RuntimeException("Unable to create Document due to aPassenger. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    passenger = aPassenger;
  }

  public Document(String aDocID, String aDocType, String aPassIDForPassenger, String aNameForPassenger, String aEmailForPassenger, String aPhoneForPassenger, String aAddressForPassenger)
  {
    docID = aDocID;
    docType = aDocType;
    passenger = new Passenger(aPassIDForPassenger, aNameForPassenger, aEmailForPassenger, aPhoneForPassenger, aAddressForPassenger, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDocID(String aDocID)
  {
    boolean wasSet = false;
    docID = aDocID;
    wasSet = true;
    return wasSet;
  }

  public boolean setDocType(String aDocType)
  {
    boolean wasSet = false;
    docType = aDocType;
    wasSet = true;
    return wasSet;
  }

  public String getDocID()
  {
    return docID;
  }

  public String getDocType()
  {
    return docType;
  }
  /* Code from template association_GetOne */
  public Passenger getPassenger()
  {
    return passenger;
  }

  public void delete()
  {
    Passenger existingPassenger = passenger;
    passenger = null;
    if (existingPassenger != null)
    {
      existingPassenger.delete();
    }
  }

  // line 160 "model.ump"
   public void validateDocument(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "docID" + ":" + getDocID()+ "," +
            "docType" + ":" + getDocType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "passenger = "+(getPassenger()!=null?Integer.toHexString(System.identityHashCode(getPassenger())):"null");
  }
}