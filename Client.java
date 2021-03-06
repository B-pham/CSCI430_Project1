import java.io.*;
import java.util.*;

public class Client implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final String ClientString = "CL";
  private String name;
  private String address;
  private String phone;
  private String id;
  private double balance = .0;

  private List<Product> shopCart = new LinkedList<Product>();
  private List<Order> orders = new LinkedList<Order>();
  private List<Invoice> invoices = new LinkedList<Invoice>();

  //private Map<Product, Double> shoppingCart = new HashMap<Product, Double>();

  public Client(String name, String address, String Phone) {
    this.name = name;
    this.address = address;
    this.phone = Phone;
    this.id = ClientString + (MemberIdServer.instance()).getId();
    this.balance = 1000.0;
  }

  /**
   * get the total amount of the shopping cart
   * @return
   */
  public double getTotal() {
    double total = .0;

    for (Product prod : shopCart) {
      total += prod.getPrice();
    }

    return total;
  }
  
  /**
  *add payment to client account
  */
  public void addPayment(double amount){
    balance += amount;
  }
  
  public List<Product> getShopCart() {
    return this.shopCart;
  }

  /**
   * reduce balance after purchase
   */
  public boolean reduceBalance(double amount) {
    System.out.println("Reducing client balance  by: $" + amount);
    balance -= amount;
    System.out.println("The balance is now: $" + balance);
    return true;
  }

  /**
   * add order to list of orders
   * @param order
   * @return
   */
  public boolean addOrder(Order order) {
    return orders.add(order);
  }

  /**
   * add invoice
   * @param invoice
   * @return
   */
  public boolean addInvoice(Invoice invoice) {
    System.out.println(name + " received invoice " + invoice.getId());
    System.out.println("The total of that invoice is $" + invoice.getTotal());
    return invoices.add(invoice);
  }

  /**
   * display shopping cart centent
   */
  public void displayCartContent() {
    for (Product prod : shopCart) {
      System.out.println(prod.toString());
    }
  }

  /**
   * clear shopping cart
   */
  public void clearShoppingCart() {
    System.out.println("Clearing shopping cart...");
    shopCart.clear();
  }

  /**
   * add product to the shopping cart
   */
  public boolean addToCart(Product product) {
    return shopCart.add(product);
  }

  public String getName() {
    return name;
  }
  
  public double getBalance(){
    return balance;
  }
  
  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setBalance(Double toBeAdded)
  {
    this.balance = toBeAdded;
  }

  public Double getBalance()
  {
    return balance;
  }

  @Override
  public String toString() {
    return (
      "Client{" +
      "name=" +
      name +
      ", address=" +
      address +
      ", phone=" +
      phone +
      ", id=" +
      id +
      ", shopCart=" +
      shopCart +
      "}"
    );
  }
}
