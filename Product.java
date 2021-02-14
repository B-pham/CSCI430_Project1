import java.io.*;

public class Product implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String name;
  private double price;
  private String id;

  public Product(String name, double price, String id) {
    this.name = name;
    this.price = price;
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Name: " + name + ", Price: " + price + ", ID: " + id;
  }
}
