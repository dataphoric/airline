package edu.neu.demo.entity;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

import java.util.*;
import java.sql.Date;

// line 2 "model.ump"
// line 190 "model.ump"
public class Passenger
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Passenger Attributes
  private String passID;
  private String name;
  private String email;
  private String phone;
  private String address;

  //Passenger Associations
  private List<Refund> refunds;
  private List<Booking> bookings;
  private List<BoardingPass> boardingPasses;
  private Document document;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Passenger(String aPassID, String aName, String aEmail, String aPhone, String aAddress, Document aDocument)
  {
    passID = aPassID;
    name = aName;
    email = aEmail;
    phone = aPhone;
    address = aAddress;
    refunds = new ArrayList<Refund>();
    bookings = new ArrayList<Booking>();
    boardingPasses = new ArrayList<BoardingPass>();
    if (aDocument == null || aDocument.getPassenger() != null)
    {
      throw new RuntimeException("Unable to create Passenger due to aDocument. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    document = aDocument;
  }

  public Passenger(String aPassID, String aName, String aEmail, String aPhone, String aAddress, String aDocIDForDocument, String aDocTypeForDocument)
  {
    passID = aPassID;
    name = aName;
    email = aEmail;
    phone = aPhone;
    address = aAddress;
    refunds = new ArrayList<Refund>();
    bookings = new ArrayList<Booking>();
    boardingPasses = new ArrayList<BoardingPass>();
    document = new Document(aDocIDForDocument, aDocTypeForDocument, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassID(String aPassID)
  {
    boolean wasSet = false;
    passID = aPassID;
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

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhone(String aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getPassID()
  {
    return passID;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getAddress()
  {
    return address;
  }
  /* Code from template association_GetMany */
  public Refund getRefund(int index)
  {
    Refund aRefund = refunds.get(index);
    return aRefund;
  }

  public List<Refund> getRefunds()
  {
    List<Refund> newRefunds = Collections.unmodifiableList(refunds);
    return newRefunds;
  }

  public int numberOfRefunds()
  {
    int number = refunds.size();
    return number;
  }

  public boolean hasRefunds()
  {
    boolean has = refunds.size() > 0;
    return has;
  }

  public int indexOfRefund(Refund aRefund)
  {
    int index = refunds.indexOf(aRefund);
    return index;
  }
  /* Code from template association_GetMany */
  public Booking getBooking(int index)
  {
    Booking aBooking = bookings.get(index);
    return aBooking;
  }

  public List<Booking> getBookings()
  {
    List<Booking> newBookings = Collections.unmodifiableList(bookings);
    return newBookings;
  }

  public int numberOfBookings()
  {
    int number = bookings.size();
    return number;
  }

  public boolean hasBookings()
  {
    boolean has = bookings.size() > 0;
    return has;
  }

  public int indexOfBooking(Booking aBooking)
  {
    int index = bookings.indexOf(aBooking);
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
  /* Code from template association_GetOne */
  public Document getDocument()
  {
    return document;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRefunds()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Refund addRefund(String aRefundID, int aRefundAmt, Date aDate, Payment aPayment)
  {
    return new Refund(aRefundID, aRefundAmt, aDate, this, aPayment);
  }

  public boolean addRefund(Refund aRefund)
  {
    boolean wasAdded = false;
    if (refunds.contains(aRefund)) { return false; }
    Passenger existingPassenger = aRefund.getPassenger();
    boolean isNewPassenger = existingPassenger != null && !this.equals(existingPassenger);
    if (isNewPassenger)
    {
      aRefund.setPassenger(this);
    }
    else
    {
      refunds.add(aRefund);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRefund(Refund aRefund)
  {
    boolean wasRemoved = false;
    //Unable to remove aRefund, as it must always have a passenger
    if (!this.equals(aRefund.getPassenger()))
    {
      refunds.remove(aRefund);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRefundAt(Refund aRefund, int index)
  {  
    boolean wasAdded = false;
    if(addRefund(aRefund))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRefunds()) { index = numberOfRefunds() - 1; }
      refunds.remove(aRefund);
      refunds.add(index, aRefund);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRefundAt(Refund aRefund, int index)
  {
    boolean wasAdded = false;
    if(refunds.contains(aRefund))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRefunds()) { index = numberOfRefunds() - 1; }
      refunds.remove(aRefund);
      refunds.add(index, aRefund);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRefundAt(aRefund, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookings()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Booking addBooking(String aBookingID, Date aBookingDate, String aPaymentInfo, Seat aSeat, Payment aPayment)
  {
    return new Booking(aBookingID, aBookingDate, aPaymentInfo, this, aSeat, aPayment);
  }

  public boolean addBooking(Booking aBooking)
  {
    boolean wasAdded = false;
    if (bookings.contains(aBooking)) { return false; }
    Passenger existingPassenger = aBooking.getPassenger();
    boolean isNewPassenger = existingPassenger != null && !this.equals(existingPassenger);
    if (isNewPassenger)
    {
      aBooking.setPassenger(this);
    }
    else
    {
      bookings.add(aBooking);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBooking(Booking aBooking)
  {
    boolean wasRemoved = false;
    //Unable to remove aBooking, as it must always have a passenger
    if (!this.equals(aBooking.getPassenger()))
    {
      bookings.remove(aBooking);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookingAt(Booking aBooking, int index)
  {  
    boolean wasAdded = false;
    if(addBooking(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookingAt(Booking aBooking, int index)
  {
    boolean wasAdded = false;
    if(bookings.contains(aBooking))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
      bookings.remove(aBooking);
      bookings.add(index, aBooking);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookingAt(aBooking, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBoardingPasses()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBoardingPass(BoardingPass aBoardingPass)
  {
    boolean wasAdded = false;
    if (boardingPasses.contains(aBoardingPass)) { return false; }
    boardingPasses.add(aBoardingPass);
    if (aBoardingPass.indexOfPassenger(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBoardingPass.addPassenger(this);
      if (!wasAdded)
      {
        boardingPasses.remove(aBoardingPass);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBoardingPass(BoardingPass aBoardingPass)
  {
    boolean wasRemoved = false;
    if (!boardingPasses.contains(aBoardingPass))
    {
      return wasRemoved;
    }

    int oldIndex = boardingPasses.indexOf(aBoardingPass);
    boardingPasses.remove(oldIndex);
    if (aBoardingPass.indexOfPassenger(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBoardingPass.removePassenger(this);
      if (!wasRemoved)
      {
        boardingPasses.add(oldIndex,aBoardingPass);
      }
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
    for(int i=refunds.size(); i > 0; i--)
    {
      Refund aRefund = refunds.get(i - 1);
      aRefund.delete();
    }
    for(int i=bookings.size(); i > 0; i--)
    {
      Booking aBooking = bookings.get(i - 1);
      aBooking.delete();
    }
    ArrayList<BoardingPass> copyOfBoardingPasses = new ArrayList<BoardingPass>(boardingPasses);
    boardingPasses.clear();
    for(BoardingPass aBoardingPass : copyOfBoardingPasses)
    {
      aBoardingPass.removePassenger(this);
    }
    Document existingDocument = document;
    document = null;
    if (existingDocument != null)
    {
      existingDocument.delete();
    }
  }

  // line 12 "model.ump"
   public void createAccount(){
    
  }

  // line 15 "model.ump"
   public void createBooking(){
    
  }

  // line 18 "model.ump"
   public void viewBooking(){
    
  }

  // line 20 "model.ump"
   public void printBoardingPass(){
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "passID" + ":" + getPassID()+ "," +
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "phone" + ":" + getPhone()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "document = "+(getDocument()!=null?Integer.toHexString(System.identityHashCode(getDocument())):"null");
  }
}