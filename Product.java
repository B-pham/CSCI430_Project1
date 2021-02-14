import java.io.*;

public class Product implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String productName;
  private double price;
  private String id;

  public Product(String name, double price, String id) {
    this.productName = name;
    this.price = price;
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return productName;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Name: " + productName + ", Price: " + price + ", ID: " + id;
  }
}
