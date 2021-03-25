import java.io.*;
import java.util.*;

public class ShipmentList implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Shipment> shipments = new LinkedList<Shipment>();
  private static ShipmentList shipmentList;
  private int shipmentCount = 0;

  private ShipmentList() {}

  public static ShipmentList instance() {
    if (shipmentList == null) {
      return (shipmentList = new ShipmentList());
    } else {
      return shipmentList;
    }
  }

  public boolean insertShipment(Shipment shipment) {
    shipmentCount += 1;
    System.out.println(shipmentCount + " shipment");
    boolean result = shipments.add(shipment);
    System.out.println(" shipments " + result);
    return result;
  }

  public Iterator<Shipment> getShipments() {
    return shipments.iterator();
  }

  /**
   * get number of shipments in the list
   */
  public int getShipmentCount() {
    return shipmentCount;
  }

  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(shipmentList);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (shipmentList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (shipmentList == null) {
          shipmentList = (ShipmentList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }

  /**
   * find shipment by id
   */
  public Shipment findShipment(String shipmentId) {
    System.out.println("received " + shipmentId);

    for (int i = 0; i < shipments.size(); i++) {
      System.out.println("searching shipme t...");
      Shipment shipment = (Shipment) shipments.get(i);

      if (shipment.getId().equals(shipmentId)) {
        System.out.println("**** found it...");
        return shipment;
      }
    }
    System.out.println("didn't find anything");
    return null;
  }

  public void getAllShipment() {
    Iterator<Shipment> ShipmentID = this.getShipments();

    System.out.println();
    while (ShipmentID.hasNext()) {
      System.out.println(ShipmentID.next().getId() + " ");
    }
  }
}
