import java.util.Scanner;

/**
 * Tester program for our warehouse system
 */
public class Tester {

  private static Scanner scanner;
  private static Warehouse warehouse = Warehouse.instance();

  public static void main(String[] args) {
    showTesterHeader();
    System.out.println();
    showMainMenu();

    int choice = promptMenuSelection();

    // handle selection
    switch (choice) {
      case 0:
        System.out.println(": Exiting program...");
        System.exit(0);
        break;
      case 1:
        System.out.println(": Manage Clients");
        handleClientManagement();
        break;
      case 2:
        System.out.println(": Manage Suppliers");
        handleSupplierManagement();
        break;
      case 3:
        System.out.println(": Manage Products");
        handleProductManagement();
        break;
      default:
        System.out.println(": Invalid choice");
        System.out.println("Please make a valid choice");
        break;
    }

    if (scanner != null) {
      scanner.close();
    }
  }

  /**
   * show helpful information about the program when it starts
   */
  private static void showTesterHeader() {
    System.out.println("\t\tWelcome CSCI Warehouse");
    System.out.print("You can use this program to manage ");
    System.out.println("clients, suppliers, and products");
  }

  /**
   * show the main menu of the program
   */
  private static void showMainMenu() {
    System.out.println("\t\tMAIN MENU");
    System.out.println();
    System.out.println("SELECT:");
    System.out.println("1 - To manage clients");
    System.out.println("2 - To manage suppliers");
    System.out.println("3 - To manage products");
    System.out.println("0 - To exit the program");
  }

  /**
   * prompt for menu selection
   *
   * @return selected menue
   */
  private static int promptMenuSelection() {
    scanner = new Scanner(System.in);
    System.out.print("::=>: ");

    int choice = scanner.nextInt();

    // check for validity
    System.out.println();
    System.out.print("You selected " + choice);

    return choice;
  }

  /**
   * Handle client management process
   */
  private static void handleClientManagement() {
    boolean keepAdding = false;
    do { // show menu
      System.out.println();
      System.out.println("CLIENT MANAGEMENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - To add a client");
      System.out.println("2 - To find a client by id");

      //get user choice
      int choice = promptMenuSelection();

      switch (choice) {
        case 1:
          System.out.println("ADD CLIENT");

          //prompt for customer details
          String name;
          String address;
          String phone;
          String id;

          System.out.print("Enter id: ");
          scanner.nextLine();
          id = scanner.nextLine();

          System.out.print("Enter name: ");
          name = scanner.nextLine();

          System.out.print("Enter address: ");
          address = scanner.nextLine();

          System.out.print("Enter phone: ");
          phone = scanner.nextLine();

          //we client data
          warehouse.addClient(name, address, phone, id);

          int count = warehouse.getClientsCount();

          System.out.println("Client count is now " + count);

          break;
        case 2:
          String clientId;
          System.out.print("\nEnter the client's id: ");
          clientId = scanner.next();

          Client client = warehouse.findClient(clientId);

          if (client == null) {
            System.out.println("No client found");
          } else {
            System.out.println("The client found is " + client.toString());
          }
          break;
        default:
          break;
      }

      //update user choice
      System.out.print("Would you like to continue ");
      System.out.println("with the clients management?");
      System.out.println();
      System.out.print("Enter 1 for yes. Anything else for no:");

      int res = scanner.nextInt();
      if (res == 1) keepAdding = true; else keepAdding = false;
    } while (keepAdding);
  }

  /**
   * handle supplier management process
   */
  private static void handleSupplierManagement() {
    boolean keepAdding = false;
    do {
      // show menu
      System.out.println();
      System.out.println("SUPPLIER MANAGEMENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - To add a supplier");
      System.out.println("2 - To find a supplier by id");
      //get user choice
      int choice = promptMenuSelection();

      switch (choice) {
        case 1:
          System.out.println("ADD SUPPLIER");

          //prompt for customer details
          String name;
          String address;
          String phone;
          String id;

          System.out.print("Enter id: ");
          scanner.nextLine();
          id = scanner.nextLine();

          System.out.print("Enter name: ");
          name = scanner.nextLine();

          System.out.print("Enter address: ");
          address = scanner.nextLine();

          System.out.print("Enter phone: ");
          phone = scanner.nextLine();

          //we client data
          warehouse.addSupplier(name, address, phone, id);

          int count = warehouse.getSuppliersCount();

          System.out.println("supplier count is now " + count);

          break;
        case 2:
          String supplierId;
          System.out.print("\nEnter the supplier's id: ");
          supplierId = scanner.next();

          Supplier supplier = warehouse.findSupplier(supplierId);

          if (supplier == null) {
            System.out.println("No supplier found");
          } else {
            System.out.println("The supplier found is " + supplier.toString());
          }
          break;
        default:
          break;
      }

      //update user choice
      System.out.print("Would you like to continue ");
      System.out.println("with the clients management?");
      System.out.println();
      System.out.print("Enter 1 for yes. Anything else for no:");

      int res = scanner.nextInt();
      if (res == 1) keepAdding = true; else keepAdding = false;
    } while (keepAdding);
  }

  /**
   * handle product management process
   */
  private static void handleProductManagement() {
    // show menu
    System.out.println();
    System.out.println("PRODUCT MANAGEMENT MENU");
    System.out.println();
    System.out.println("SELECT:");
    System.out.println("1 - To add a product");
    System.out.println("2 - To find a priduct by id");
    System.out.println("3 - To list all products");

    int choice = promptMenuSelection();
  }
}
