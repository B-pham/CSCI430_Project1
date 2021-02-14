import java.io.Serializable;

public class Warehouse implements Serializable {

  private static final long serialVersionUID = 1L;
  private static ClientList clientList = ClientList.instance();
  private static SupplierList supplierList = SupplierList.instance();
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
   * @param id
   * @return
   */
  public boolean addClient(
    String name,
    String address,
    String phone,
    String id
  ) {
    Client client = new Client(name, address, phone, id);
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
   * @param id
   * @return
   */
  public boolean addSupplier(
    String name,
    String address,
    String phone,
    String id
  ) {
    Supplier supplier = new Supplier(name, address, phone, id);
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
}
