import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Tester program for our warehouse system
 */
public class Tester {

  private static Scanner scanner;
  private static Warehouse warehouse = Warehouse.instance();

  public static void main(String[] args) {
    boolean keepWorking = true;
    do {
      showTesterHeader();
      System.out.println();

      showMainMenu();

      int choice = promptMenuSelection();

      // handle selection
      switch (choice) {
        case 0:
          System.out.println(": Exiting program...");
          keepWorking = false;
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
        case 4:
          System.out.println(": Manage Transactions");
          handleTransactionManagement();
          break;
        case 5:
          populateTestData();
          break;
        default:
          System.out.println(": Invalid choice");
          System.out.println("Please make a valid choice");
          continue;
      }
    } while (keepWorking);

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
    System.out.println("4 - To manage transactions");
    System.out.println("5 - To populate test data");
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
    boolean keepWorking = false;
    loop:do { // show menu
      System.out.println();
      System.out.println("CLIENT MANAGEMENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - To add a client");
      System.out.println("2 - To find a client by id");
      System.out.println("3 - To display all clients");
      System.out.println("4 - To add product to client shopping cart");
      System.out.println("5 - To display client shopping cart");
      System.out.println("6 - To place order");
      System.out.println("0 - To go back");

      //get user choice
      int choice = promptMenuSelection();

      switch (choice) {
        case 0:
          System.out.println("Back to the main menu");
          break loop;
        case 1:
          System.out.println(" ADD CLIENT");

          //prompt for customer details
          String name;
          String address;
          String phone;

          System.out.print("Enter name: ");
          scanner.nextLine();
          name = scanner.nextLine();

          System.out.print("Enter address: ");
          address = scanner.nextLine();

          System.out.print("Enter phone: ");
          phone = scanner.nextLine();

          //we client data
          warehouse.addClient(name, address, phone);

          int count = warehouse.getClientsCount();

          System.out.println("Client count is now " + count);

          break;
        case 2:
          System.out.println(" SEARCH CLIENT");
          System.out.println();

          String clientId;
          System.out.print("Enter the client's id: ");
          clientId = scanner.next();

          Client client = warehouse.findClient(clientId);

          if (client == null) {
            System.out.println("No client found");
          } else {
            System.out.println("The client found is " + client.toString());
          }
          break;
        case 3:
          System.out.println(" DISPLAY CLIENTS");
          System.out.println();
          warehouse.displayClients();
          break;
        case 4:
          System.out.println(" ADD PRODUCT TO CLIENT CART");
          System.out.println();

          //display list of clients
          warehouse.displayClients();
          System.out.println();

          //select a client
          System.out.println(" Select a client by Id");

          //prompt for selection
          String selectedId = scanner.next();

          //find client
          Client selectedClient = warehouse.findClient(selectedId);

          if (selectedClient == null) {
            System.out.println("No client found");
            continue;
          }

          System.out.println("You selected:");
          System.out.println(selectedClient.toString());
          System.out.println();

          boolean keepAdding = true;

          do {
            //display products for selection
            warehouse.displayProducts();
            System.out.println();

            //select a product
            System.out.println(" Select a product by Id");
            String selectedProd = scanner.next();

            //find product
            Product selectedProduct = warehouse.findProduct(selectedProd);

            if (selectedProduct == null) {
              System.out.println("No product found");
              continue;
            }

            System.out.println(
              "Adding... " + selectedProduct.getName() + " to cart"
            );

            selectedClient.addToCart(selectedProduct);

            System.out.println(
              "Updated total amount: " + selectedClient.getTotal()
            );

            System.out.print("Add more product?");
            System.out.println();
            System.out.print("Enter y for yes. Anything else for no: ");
            String res = scanner.next();
            if (res.equals("y")) keepAdding = true; else keepAdding = false;
          } while (keepAdding);

          //display shopping cart content
          System.out.println();
          System.out.println("Shopping cart content");
          selectedClient.displayCartContent();
          System.out.println();

          //display cart summary
          System.out.print("The order total is: ");
          System.out.println("$" + selectedClient.getTotal());
          System.out.println();

          System.out.println("Would you like to place the order?");
          String placeOrder = scanner.next();
          if (placeOrder.equals("y")) {
            boolean received = warehouse.receiveOrder(selectedClient);

            if (received) {
              System.out.println();
              System.out.println();
            } else {
              System.out.println("We were unable to receive your order");
              System.out.println();
              break;
            }
          }

          break;
        default:
          System.out.println("That is not a valid input.");
          continue;
      }

      //update user choice
      System.out.print("Would you like to continue ");
      System.out.println("with the clients management?");
      System.out.println();
      System.out.print("Enter y for yes. Anything else for no: ");

      String res = scanner.next();
      if (res.equals("y")) keepWorking = true; else keepWorking = false;
    } while (keepWorking);
  }

  /**
   * handle supplier management process
   */
  private static void handleSupplierManagement() {
    boolean keepWorking = false;
    loop:do {
      // show menu
      System.out.println();
      System.out.println("SUPPLIER MANAGEMENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - To add a supplier");
      System.out.println("2 - To find a supplier by id");
      System.out.println("3 - To display suppliers");
      System.out.println("0 - To go back");

      //get user choice
      int choice = promptMenuSelection();

      switch (choice) {
        case 0:
          System.out.println("Back to the main menu");
          break loop;
        case 1:
          System.out.println(" ADD SUPPLIER");
          System.out.println();

          //prompt for customer details
          String name;
          String address;
          String phone;

          System.out.print("Enter name: ");
          scanner.nextLine();
          name = scanner.nextLine();

          System.out.print("Enter address: ");
          address = scanner.nextLine();

          System.out.print("Enter phone: ");
          phone = scanner.nextLine();

          //we client data
          warehouse.addSupplier(name, address, phone);

          int count = warehouse.getSuppliersCount();

          System.out.println("supplier count is now " + count);

          break;
        case 2:
          System.out.println(" SEARCH SUPPLIER");
          System.out.println();

          String supplierId;
          System.out.print("Enter the supplier's id: ");
          supplierId = scanner.next();

          Supplier supplier = warehouse.findSupplier(supplierId);

          if (supplier == null) {
            System.out.println("No supplier found");
          } else {
            System.out.println("The supplier found is " + supplier.toString());
          }
          break;
        case 3:
          System.out.println(" DISPLAY SUPPLIERS");
          System.out.println();
          warehouse.displaySuppliers();
          break;
        default:
          System.out.println("That is not a valid input.");
          continue;
      }

      //update user choice
      System.out.print("Would you like to continue ");
      System.out.println("with the su plier management?");
      System.out.println();
      System.out.print("Enter y for yes. Anything else for no: ");

      String res = scanner.next();
      if (res.equals("y")) keepWorking = true; else keepWorking = false;
    } while (keepWorking);
  }

  /**
   * handle product management process
   */
  private static void handleProductManagement() {
    boolean keepWorking = false;
    loop:do {
      // show menu
      System.out.println();
      System.out.println("PRODUCT MANAGEMENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - To add a product");
      System.out.println("2 - To find a product by id");
      System.out.println("3 - To display products");
      System.out.println("4 - To display products by supplier");
      System.out.println("0 - To go back");

      int choice = promptMenuSelection();

      switch (choice) {
        case 0:
          System.out.println("Back to the main menu");
          break loop;
        case 1:
          System.out.println(" ADD PRODUCT");
          System.out.println();

          //prompt for product details
          String name;
          double price;
          String supplierID;
          int quantity = 1;

          System.out.print("Enter name: ");
          scanner.nextLine();
          name = scanner.nextLine();

          System.out.print("Enter price: ");
          price = scanner.nextDouble();

          System.out.print("Enter quantity: ");
          scanner.nextLine();
          quantity = scanner.nextInt();

          System.out.print("Enter supplier ID: ");
          scanner.nextLine();
          supplierID = scanner.nextLine();

          //we client data
          if (warehouse.addProduct(name, price, supplierID, quantity) == true) {
            int count = warehouse.getProductsCount();
            System.out.println("products count is now " + count);
          }

          break;
        case 2:
          System.out.println(" SEARCH PRODUCT");
          System.out.println();

          String productId;
          System.out.print("\nEnter the product's id: ");
          productId = scanner.next();

          Product product = warehouse.findProduct(productId);

          if (product == null) {
            System.out.println("No product found");
          } else {
            System.out.println("The product found is " + product.toString());
          }
        case 3:
          System.out.println(" DISPLAY PRODUCTS");
          System.out.println();
          warehouse.displayProducts();
          break;
        case 4:
          System.out.println(" DISPLAY PRODUCTS BY SUPPLIER");
          System.out.println();

          String supplierId;
          System.out.print("\nEnter the supplier's id: ");
          supplierId = scanner.next();

          warehouse.displayProductsBySupplier(supplierId);
          break;
        default:
          System.out.println("That is not a valid input.");
          continue;
      }

      //update user choice
      System.out.print("Would you like to continue ");
      System.out.println("with the product management?");
      System.out.println();
      System.out.print("Enter y for yes. Anything else for no: ");

      String res = scanner.next();
      if (res.equals("y")) keepWorking = true; else keepWorking = false;
    } while (keepWorking);
  }

  /**
   * handle transaction management
   */
  private static void handleTransactionManagement() {
    boolean keepWorking = false;
    loop:do {
      // show menu
      System.out.println();
      System.out.println("TRANSACTION MANAGEMENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - To display all transactions");
      System.out.println("2 - To display transactions by client");
      System.out.println("0 - To go back");

      int choice = promptMenuSelection();

      switch (choice) {
        case 0:
          System.out.println("Back to the main menu");
          break loop;
        case 1:
          System.out.println(" DISPLAY ALL PRODUCTS");
          System.out.println();
          warehouse.displayTransactions();
          break;
        case 2:
          System.out.println(" DISPLAY TRANSACTION BY CLIENT");
          System.out.println();
          String clientId;
          System.out.print("\nEnter the client's id: ");
          clientId = scanner.next();
          warehouse.displayTransactionsByClient(clientId);
          break;
        default:
          System.out.println("That is not a valid input.");
          continue;
      }

      //update user choice
      System.out.print("Would you like to continue ");
      System.out.println("with the transaction management?");
      System.out.println();
      System.out.print("Enter y for yes. Anything else for no: ");

      String res = scanner.next();
      if (res.equals("y")) keepWorking = true; else keepWorking = false;
    } while (keepWorking);
  }

  /**
   * populate with static data
   */
  private static void populateTestData() {
    //populate suppliers
    List<String> sups = new LinkedList<String>();
    sups.add("Jacob");
    sups.add("James");
    sups.add("Bill");
    sups.add("Mamadou");
    sups.add("Tom");
    for (int i = 0; i < sups.size(); i++) {
      warehouse.addSupplier(sups.get(i), i + " Main Street", "320223589" + i);
    }

    //populate clients
    List<String> clnts = new LinkedList<String>();
    clnts.add("Jane");
    clnts.add("Diana");
    clnts.add("Claire");
    clnts.add("Elias");

    for (int i = 0; i < clnts.size(); i++) {
      warehouse.addClient(clnts.get(i), i + " Big Avenue", "652223589" + i);
    }

    //populate products
    List<String> prods = new LinkedList<String>();
    prods.add("Ice Cream");
    prods.add("Apple Juice");
    prods.add("Yogurt XL");
    prods.add("Chips Doritos");
    prods.add("Pure Water");
    prods.add("Elias");

    for (int i = 0; i < prods.size(); i++) {
      warehouse.addProduct(prods.get(i), (i * 2 + 6.5), "SP" + i, i * 2);
    }
  }
}
