import java.io.*;
import java.util.*;

public class ClientList implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Client> clients = new LinkedList<Client>();
  private static ClientList clientList;
  private int clientCount = 0;

  private ClientList() {}

  public static ClientList instance() {
    if (clientList == null) {
      return (clientList = new ClientList());
    } else {
      return clientList;
    }
  }

  public boolean insertClient(Client client) {
    clientCount += 1;
    System.out.println(clientCount + " clients");
    boolean result = clients.add(client);
    System.out.println(" clients " + result);
    return result;
  }

  public Iterator<Client> getClients() {
    return clients.iterator();
  }

  /**
   * get number of clients in the list
   */
  public int getClientsCount() {
    return clientCount;
  }

  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (clientList != null) {
        return;
      } else {
        input.defaultReadObject();
        if (clientList == null) {
          clientList = (ClientList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }

  /**
   * find client by id
   */
  public Client findClient(String clientId) {
    System.out.println("received " + clientId);

    for (int i = 0; i < clients.size(); i++) {
      System.out.println("searching client...");
      Client client = (Client) clients.get(i);

      if (client.getId().equals(clientId)) {
        System.out.println("**** found it...");
        return client;
      }
    }
    System.out.println("didn't find anything");
    return null;
  }
}
