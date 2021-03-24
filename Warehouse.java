import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;

public class Warehouse implements Serializable {

  private static final long serialVersionUID = 1L;
  private static ClientList clientList = ClientList.instance();
  private static SupplierList supplierList = SupplierList.instance();
  private static ProductList productList = ProductList.instance();
  private static Warehouse warehouse;
  private static List<Transaction> transactions = new LinkedList<Transaction>();
  private static List<Order> orders = new LinkedList<Order>();
  private List<Product> waitlistOrders;
  private Warehouse() {}

  public static Warehouse instance() {
    if (warehouse == null) {
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }

  /**
   * add client to client list
   * @param name
   * @param address
   * @param phone
   * @return
   */
  public boolean addClient(String name, String address, String phone) {
    Client client = new Client(name, address, phone);
    return clientList.insertClient(client);
  }

  /**
   * display all clients
   */
  public void displayClients() {
    Iterator<Client> clients = clientList.getClients();
    if (clients.hasNext()) {
      while (clients.hasNext()) {
        System.out.println(clients.next().toString());
      }
    } else {
      System.out.println("There is nothing in your client list");
    }
  }

  /**
   * get number of client in the list
   */
  public int getClientsCount() {
    return clientList.getClientsCount();
  }

  public Client findClient(String clientId) {
    return clientList.findClient(clientId);
  }

  public boolean checkClients(String clientID){
    if(getClientsCount() == 0){
      System.out.println("There are no clients in the list.");
      return false;
    }
    else
      return true;
  }

  public void showClient(Client client){
    System.out.println(client.toString());
  }

  /**
   * display all products
   */
  public void displayProducts() {
    Iterator<Product> products = productList.getProducts();
    if (products.hasNext()) {
      while (products.hasNext()) {
        System.out.println(products.next().toString());
      }
    } else {
      System.out.println("There is nothing in your product list");
    }
  }

  /**
   * display all suppliers
   */
  public void displaySuppliers() {
    Iterator<Supplier> suppliers = supplierList.getSuppliers();
    if (suppliers.hasNext()) {
      while (suppliers.hasNext()) {
        System.out.println(suppliers.next().toString());
      }
    } else {
      System.out.println("There is nothing in your supplier list");
    }
  }

  /**
   * display products by supplier
   */
  public void displayProductsBySupplier(String supplierId) {
    Iterator<Product> products = productList.getProducts();
    if (products.hasNext()) {
      while (products.hasNext()) {
        Product product = products.next();
        if (product.getSupplierID().equals(supplierId)) {
          System.out.println(product.toString());
        }
      }
    } else {
      System.out.println("There is nothing in your product list");
    }
  }

  /**
   * display transaction by client
   */
  public void displayTransactionsByClient(String clientId) {
    if (!transactions.isEmpty()) {
      transactions.forEach(
        transaction -> {
          if (transaction.getClientId().equals(clientId)) {
            System.out.println(transaction.toString());
          }
        }
      );
    } else {
      System.out.println("There is no transaction to display for this client");
    }
  }

  /**
   * display all transactions
   */
  public void displayTransactions() {
    if (!transactions.isEmpty()) {
      transactions.forEach(
        transaction -> {
          System.out.println(transaction.toString());
        }
      );
    } else {
      System.out.println("There is nothing in your transaction list");
    }
  }

  /**
   * receive client order
   * @param client
   * @return
   */
  public boolean receiveOrder(Client client) {
    if (client.getShopCart().isEmpty()) {
      return false;
    }

    //PROCESS ORDER HERE
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
      "E, MMM dd yyyy HH:mm:ss"
    );
    String formattedDate = date.format(dateFormat);

    //create order and send a copy to client
    Order order = new Order(client);
    client.addOrder(order); //client copy
    orders.add(order); //warehouse copy
    System.out.println("Order " + order.getId() + " received");

    //create transaction and send to client
    Transaction transaction = new Transaction(
      client.getId(),
      formattedDate,
      client.getTotal()
    );
    transactions.add(transaction); //warehouse copy
    System.out.println("Transaction " + transaction.getId() + " added");

    //create invoice and send to client
    Invoice invoice = new Invoice(
      client.getId(),
      formattedDate,
      client.getShopCart(),
      client.getTotal()
    );
    System.out.println("Invoice " + invoice.getId() + " generated");
    client.addInvoice(invoice); //client copy
    System.out.println();

    //Waitlisting
    waitlistOrders = order.getProducts();
    double runningTotal = 0.0;

    for(int i = 0; i< waitlistOrders.size(); i++){
      String temp = waitlistOrders.get(i).getId();
      Product updateQuantity = productList.findProduct(temp);//finds the product in the list and points to it
      if(updateQuantity.getQuantity() == 0){
        String waitlist = invoice.getClientId();
        updateQuantity.addToList(waitlist);// checks if it has stock, if not then it gets added to the waitlist
        System.out.println("There is none left in stock, you will be put on the waiting list.");
        
        runningTotal += updateQuantity.getPrice();
      }
      else{
        updateQuantity.setQuantity(-1);// else continues to remove stock
      }
      updateQuantity.getAllInList();
      
      
    }
    
    


    //process payment
    //reduce client balance
    client.reduceBalance(client.getTotal() - runningTotal);
    System.out.println();

    System.out.println("The shipment is now been prepared...");

    //clear shopping cart
    client.clearShoppingCart();

    //prepare shipment
    return true;
  }


  /**
   * add supplier to suppliers list
   * @param name
   * @param address
   * @param phone
   * @return
   */
  public boolean addSupplier(String name, String address, String phone) {
    Supplier supplier = new Supplier(name, address, phone);
    return supplierList.insertSupplier(supplier);
  }

  /**
   * get number of suppliers in the list
   */
  public int getSuppliersCount() {
    return supplierList.getSuppliersCount();
  }

  /**
   * find a supplier by id
   */
  public Supplier findSupplier(String supplierId) {
    return supplierList.findSupplier(supplierId);
  }

  /**
   * add product to products list
   * @param name
   * @param price
   * @return
   */
  public boolean addProduct(
    String name,
    double price,
    String supplierID,
    int quantity
  ) {
    if (this.checkSupplier(supplierID) == true) {
      Product product = new Product(name, price, supplierID, quantity);
      return productList.insertProduct(product);
    } else return false;
  }

  /**
   * get number of products in the list
   */
  public int getProductsCount() {
    return productList.getProductsCount();
  }

  /**
   * find a product by id
   */
  public Product findProduct(String productId) {
    return productList.findProduct(productId);
  }

  public boolean checkSupplier(String supplierID) {
    //Checks if the there are suppliers
    if (getSuppliersCount() == 0) {
      System.out.println("There are no suppliers to provide products.");
      return false;
    } else {
      //checks if the supplier exists in the list of suppliers
      Supplier supplier = this.findSupplier(supplierID);
      if (supplier == null) return false; else return true;
    }
  }

  public void getAllProdId() {
    productList.getAllProducts();
  }

  public Double addToBalance(Double amount, Client client)
  {
      client.setBalance(client.getBalance() + amount);

      return client.getBalance();
  }

  public void acceptOrder(){
    Scanner sc = new Scanner(System.in);
    String prodID;
    char answer;
    int size = waitlistOrders.size();//when item is removed in loop waitlistOrder size decreases
    
    System.out.println("These items are on a waitlist, do you wish to fill them? Type 'Y' or 'N'");
    for(int i = 0; i < size; i++){
      System.out.println("Item "+i);
      waitlistOrders.get(i);
      System.out.println("Do you wish to fill waitlist order?");
      answer = sc.next().charAt(0);
      
      if(answer == 'Y')
        waitlistOrders.remove(i);
    }

    if (sc != null) {
      sc.close();
    }

  }
}
