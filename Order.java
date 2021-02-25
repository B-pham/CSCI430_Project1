import java.io.*;
import java.util.*;

public class Order implements Serializable {

  private static final long serialVersionUID = 1L;
  private Client client;
  private double total;
  private List<Product> products;

  public Order(Client client) {
    this.client = client;
    this.products = client.getShopCart();
    //calculate the total of the shopping cart
  }
}
