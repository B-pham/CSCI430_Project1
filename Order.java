import java.io.*;
import java.util.*;

public class Order implements Serializable {

  private static final long serialVersionUID = 1L;
  private String id;
  private Client client;
  private double total;
  private List<Product> products;

  public Order(Client client) {
    this.client = client;
    this.products = client.getShopCart();
    this.total = client.getTotal();
    this.id = "ORD" + MemberIdServer.instance().getId();
    //calculate the total of the shopping cart
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
