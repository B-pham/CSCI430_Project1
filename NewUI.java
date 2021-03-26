import java.util.*;

public class NewUI {
    private static Scanner scanner;
    private static Warehouse warehouse = Warehouse.instance();

    public static void main(String[] args){
      Client testClient = new Client("John", "123 Ave N", "123-342-3321");
      testClient.setId("123");

      ClientList clientList = ClientList.instance();
      clientList.insertClient(testClient);

        boolean keepWorking = true;
        do{
            System.out.println("\tWelcome CSCI Warehouse");  
            showMainMenu();  

            int choice = promptMenuSelection();

            //Selection management
            switch(choice){
                case 0:
                    System.out.println(": Exiting the program...");
                    keepWorking = false;
                    break;
                case 1:
                    System.out.println();
                    System.out.print("Please enter your client ID: ");

                    scanner.nextLine();
                    String clientID = scanner.nextLine();
                    if(warehouse.checkClients(clientID) == false){
                        System.out.println("There are no clients in the list");
                        break;
                    }
                    Client client = warehouse.findClient(clientID);
                    if(client == null){
                        System.out.println("That client ID does not exist in the list.");
                        break;
                    }

                    handleClientMenu(client);
                    break; 
                case 2:
                    handleClerkMenu();
                    break;
                case 3:
                    handleManagerMenu();
                    break;
                default:
                    System.out.println(": Invalid choice");
                    System.out.println("Please make a valid choice");
                    continue;                                 
            }
        }while(keepWorking);

        if(scanner != null){
            scanner.close();
        }
    }

    //The opening state
    private static void showMainMenu(){
        System.out.println("\t\tMAIN MENU");
        System.out.println();
        System.out.println("1 - Client menu");
        System.out.println("2 - Clerk menu");
        System.out.println("3 - Manager menu");  
        System.out.println("0 - Exit program");   
    }

    //Menu selection prompts @return selected menu
    private static int promptMenuSelection() {
        scanner = new Scanner(System.in);
        System.out.print("::=>: ");
    
        int choice = scanner.nextInt();
        return choice;
    }
      
    private static void handleClientMenu(Client client){
        scanner = new Scanner(System.in);
        boolean Continue = true;
        boolean keepWorking = true;

        loop:do{
          clientMenuPrompt();
          int choice = promptMenuSelection();
          switch (choice) {
            case 0:
              System.out.println("Back to the main menu");

              break loop;
            case 1:
              System.out.println("Client details:");

              break;
            case 2:
              System.out.println("Available products: ");

              break;
            case 3:
            System.out.println("Your previous transactions: ");

              break;
            case 4:
            System.out.println("Your current shopping cart: ");

              break;
            case 5:
              System.out.println("Your current waitlisted items: ");

              break;
            default:
              System.out.println("That is not a valid input.");
              continue;
          }
        } while (keepWorking);
      }

    private static void handleClerkMenu(){
        scanner = new Scanner(System.in);
        boolean Continue = true;
        boolean keepWorking = true;

        loop:do{
          clerkMenuPrompt();
          int choice = promptMenuSelection();
          switch (choice) {
            case 0:
              System.out.println("Back to the main menu");

              break loop;
            case 1:
              System.out.println("Add Client: ");

              break;
            case 2:
              System.out.println("List Products: ");
    
              break;
            case 3:
              System.out.println("List Clients: ");
    
              break;
            case 4:
              System.out.println("List Clients with outstanding balance: ");
    
              break;
            case 5:
              System.out.println("Display product waitlist: ");
    
              break;
            case 6:
              System.out.println("Input shipment: ");
    
              break;
            case 9:
              System.out.print("Please enter your client ID: ");
              scanner.nextLine();
              String clientID = scanner.nextLine();
              if(warehouse.checkClients(clientID) == false){
                System.out.println("There are no clients in the list");
                break;
              }
              Client client = warehouse.findClient(clientID);
              if(client == null){
                  System.out.println("That client ID does not exist in the list.");
                  break;
              }

              handleClientMenu(client);
              break; 
            default:
              System.out.println("That is not a valid input.");
              continue;
          }
        } while (keepWorking);
      }

    private static void handleManagerMenu(){
        scanner = new Scanner(System.in);
        boolean Continue = true;
        boolean keepWorking = true;

        loop:do{
          managerMenuPrompt();
          int choice = promptMenuSelection();
          switch (choice) {
            case 0:
              System.out.println("Back to the main menu");

              break loop;
            case 1:
              System.out.println("Add product: ");

              break;
            case 2:
              System.out.println("Add supplier: ");

              break;
            case 3:
              System.out.println("List suppliers: ");
    
              break;
            case 4:
              System.out.println("List suppliers for product: ");
    
              break;
            case 5:
              System.out.println("List products for supplier: ");
    
              break;
            case 6:
              System.out.println("Update products and prices for supplier: ");
    
              break;
            case 9:
              handleClerkMenu();
              break;
            default:
              System.out.println("That is not a valid input.");
              continue;
          }
        } while (keepWorking);
      }

    public static void clientMenuPrompt(){
      System.out.println();
      System.out.println("\tCLIENT MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - Show details");
      System.out.println("2 - List products");
      System.out.println("3 - List transactions");
      System.out.println("4 - Modify shopping cart");
      System.out.println("5 - Display current waitlist");
      System.out.println("0 - Logout");
    }
  
    public static void clerkMenuPrompt(){
      System.out.println();
      System.out.println("\tCLERK MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - Add client");
      System.out.println("2 - List products");
      System.out.println("3 - List clients");
      System.out.println("4 - List clients with outstanding balance");
      System.out.println("5 - Display product waitlist");
      System.out.println("6 - Input shipment");
      System.out.println("9 - View client menu");
      System.out.println("0 - Logout");
    }
  
    public static void managerMenuPrompt(){
      System.out.println();
      System.out.println("\tMANAGER MENU");
      System.out.println();
      System.out.println("SELECT:");
      System.out.println("1 - Add product");
      System.out.println("2 - Add supplier");
      System.out.println("3 - List suppliers");
      System.out.println("4 - List suppliers for a product with prices");
      System.out.println("5 - List products for a supplier with prices");
      System.out.println("6 - Update products and prices for a supplier");
      System.out.println("9 - View clerk menu");
      System.out.println("0 - Logout");
    }

}