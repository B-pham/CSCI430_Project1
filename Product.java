import java.io.*;
import java.util.*;

public class Product implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private static final String ProductString = "PR";
  private String name;
  private double price;
  private int quantity;
  private String id;
  private String supplierID;

  private Queue<String> waitingList = new LinkedList<String>();

  public Product(String name, double price, String supID, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.id = ProductString + (MemberIdServer.instance()).getId();
    this.supplierID = supID;
  }

  public double getPrice() {
    return price;
  }

  public String getSupplierID() {
    return supplierID;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public void setQuantity(int amount){
    quantity += amount;
  }

  public void addToList(String toBeAdded){
     waitingList.add(toBeAdded);
  }

  public void getAllInList() {
    Iterator<String> waitlist = waitingList.iterator();
    System.out.println();
     while(waitlist.hasNext()){
        System.out.println(waitlist.next() + " ");
      } 

  } 

  //Checks to see if the clientID is on the waitlist
  public boolean check(String clientID){
    if(waitingList.contains(clientID)){
      return true;
    }
    else return false;
  }

  @Override
  public String toString() {
    return (
      "Name: " +
      name +
      ", Price: " +
      price +
      ", ID: " +
      id +
      ", Quantity: " +
      quantity +
      ", Supplier: " +
      supplierID
    );
  }
}
