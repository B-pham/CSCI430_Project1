import java.io.*;

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

  public double getQuantity() {
    return quantity;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
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
