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
            switch(choice){
                case 0:
                    System.out.println("Back to the main menu");
                    break loop;
                case 1:
                    System.out.println("Client details:");
                    warehouse.showClient(client);
                    break;
                case 2:
                    warehouse.displayProducts();
                    break;
                case 3:
                    warehouse.displayTransactionsByClient(client.getId());
                    break;
                case 4:
                    do{
                        Continue = shoppingCart(client);                        
                    }while(Continue);
                    break;
                case 5:
                    System.out.println("Your current waitlisted items: ");
                    warehouse.displayWaitlist(client.getId());
                    break;
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

    public static boolean shoppingCart(Client client){
        boolean keepGoing = true;
        String productID;

        System.out.println();
        System.out.println("Shopping cart: ");
        warehouse.showClient(client);
        System.out.println(); 
        
        System.out.println("1 - Add to cart");
        System.out.println("2 - Remove from cart");
        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                System.out.print("Please enter the product ID of the item that you would like to add: ");
                scanner.nextLine();
                productID = scanner.nextLine();
                warehouse.addtoCart(productID, client);
                break;
            case 2:
                if(client.getShopCart().isEmpty())
                    System.out.println("There is nothing to remove.");
                else{
                    System.out.print("Please enter the product ID of the item that you would like to remove: ");
                    scanner.nextLine();
                    productID = scanner.nextLine();
                    warehouse.removeFromCart(productID, client);
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;


        }
        do{
            System.out.println("Would you like to continue editing your cart? Y/N: ");
            char edit = scanner.nextLine().charAt(0);
            if (edit == 'y' || edit == 'Y')
            {
                return true;
            }
            else if(edit == 'n' ||edit == 'N'){
                return false;
            }
            else{
                System.out.println("Invalid choice please try again.");
                System.out.println();
            }     
        }while(keepGoing);

        return true;
    }

    private static void handleClerkMenu(){
        System.out.println("this is Clerk menu");
    }

    private static void handleManagerMenu(){
        System.out.println("This is the manager menu");
    }   
}



