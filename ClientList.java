import java.io.*;
import java.util.*;

public class ClientList implements Serializable {

  private static final long serialVersionUID = 1L;
  private List clients = new LinkedList();
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

  public Iterator getClients() {
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

    Iterator<Client> i = clients.iterator();

    while (i.hasNext()) {
      System.out.println("searching...");
      Client client = (Client) i.next();
      System.out.println("comparing " + client.getId() + " with " + clientId);
      if (client.getId().equals(clientId)) {
        System.out.println("**** found it...");
        return client;
      }
    }
    return null;
  }
}
