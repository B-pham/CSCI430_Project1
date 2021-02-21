import java.io.Serializable;

public class Warehouse implements Serializable {

  private static final long serialVersionUID = 1L;
  private static ClientList clientList = ClientList.instance();
  private static SupplierList supplierList = SupplierList.instance();
  private static ProductList productList = ProductList.instance();
  private static Warehouse warehouse;

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
  public boolean addClient(
    String name,
    String address,
    String phone
  ) {
    Client client = new Client(name, address, phone);
    return clientList.insertClient(client);
  }

  /**
   * get number of client in the list
   */
  public int getClientsCount() {
    return clientList.getClientsCount();
  }

  /**
   * find a client by id
   */
  public Client findClient(String clientId) {
    return clientList.findClient(clientId);
  }

  /**
   * add supplier to suppliers list
   * @param name
   * @param address
   * @param phone
   * @return
   */
  public boolean addSupplier(
    String name,
    String address,
    String phone
  ) {
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
  public boolean addProduct(String name, double price) {
    Product product = new Product(name, price);
    return productList.insertProduct(product);
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
}
