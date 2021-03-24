import java.util.*;

public class NewUI {
    private static Scanner scanner;
    private static Warehouse warehouse = Warehouse.instance();

    public static void main(String[] args){
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
                    handleClientMenu();
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
      
    private static void handleClientMenu(){
        scanner = new Scanner(System.in);
        Client client;
        String clientID;
        boolean keepWorking = true;

        System.out.println();
        System.out.print("Please enter your client ID: ");


        clientID = scanner.nextLine();
        if(warehouse.checkClients(clientID) == false){
            System.out.println("There are no clients in the list");
            return;
        }
            
        client = warehouse.findClient(clientID);
        if(client == null){
            System.out.println("That client ID does not exist in the list.");
            return;
        }

        loop:do{
            clientMenuPrompt();
            int choice = promptMenuSelection();        
            switch(choice){
                case 0:
                    System.out.println("Back to the main menu");
                    break loop;
                case 1:
                    System.out.println("Client details:");
                    warehouse.showClient(client);
                case 2:
                    warehouse.displayProducts();
                case 3:
                    warehouse.displayTransactionsByClient(clientID);
                case 4:
                case 5:
                default:
                    System.out.println("That is not a valid input.");
                    continue;
            }   
         
        }while(keepWorking);

    }

    public static void clientMenuPrompt(){
        System.out.println();
        System.out.println("\tCLIENT MENU");
        System.out.println();
        System.out.println("SELECT:");
        System.out.println("1 - Show details");
        System.out.println("2 - List products available");
        System.out.println("3 - List previous transactions");
        System.out.println("4 - Modify shopping cart");
        System.out.println("5 - Display current waitlist");
        System.out.println("0 - Logout");        
    }

    private static void handleClerkMenu(){
        System.out.println("this is Clerk menu");
    }

    private static void handleManagerMenu(){
        System.out.println("This is the manager menu");
    }   
}



