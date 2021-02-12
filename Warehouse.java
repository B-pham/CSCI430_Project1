public class Warehouse {

  private static ClientList clientList = ClientList.instance();
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
    return null;
  }
}
