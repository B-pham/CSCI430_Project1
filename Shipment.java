import java.io.*;
import java.util.*;

public class Shipment {

  String id;
  String supplierId;
  boolean isAccepted;
  List<Product> products = new LinkedList<Product>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(String supplierId) {
    this.supplierId = supplierId;
  }

  public boolean isAccepted() {
    return isAccepted;
  }

  public void setAccepted(boolean isAccepted) {
    this.isAccepted = isAccepted;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
