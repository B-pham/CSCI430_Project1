import java.util.LinkedList;
import java.util.List;

public class Invoice {

  double total;
  String date;
  String id;
  String clientId;
  private List<Product> products = new LinkedList<Product>();

  public Invoice(
    String clientId,
    String date,
    List<Product> products,
    double total
  ) {
    this.id = "INV" + MemberIdServer.instance().getId();
    this.clientId = clientId;
    this.date = date;
    this.total = total;
    this.products = products;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
