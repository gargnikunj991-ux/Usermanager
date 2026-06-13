import java.util.Scanner;
import manager.BookManager;
import manager.MemberManager;
import manager.UserManager;


public class Main {
     public static void showMainMenu() {
    System.out.println("=== Library Management System ===");
    System.out.println("1: Authentication");
    System.out.println("2: Employee Management");
    System.out.println("3: Member Management");
    System.out.println("4: Book Management");
    System.out.println("5: Library Operations");
    System.out.println("6: Exit"); 
    }

    public static void showAuthenticationMenu() {
    System.out.println("=== Authentication ===");
    System.out.println("1: Login");
    System.out.println("2: Logout");
    System.out.println("3: Login Status");
    System.out.println("4: Back");  
    } 

    public static void showEmployeeMenu(){
       System.out.println("=== Employee Management ===");
       System.out.println("1: Add user");
       System.out.println("2: Search user");
       System.out.println("3: Display user");
       System.out.println("4: Update user");
       System.out.println("5: Change username");
       System.out.println("6: Delete user");
       System.out.println("7: Back");
    }
    
    public static void showMemberManagementMenu(){
        System.out.println("=== Member Management ===");
        System.out.println("1: Add member ");
        System.out.println("2: Search member");
        System.out.println("3: Display member");
        System.out.println("4: Back");
    }

    public static void showBookManagementMenu(){
        System.out.println("=== Book Management ===");
        System.out.println("1: Add book");
        System.out.println("2: Search book");
        System.out.println("3: Display book");
        System.out.println("4: Back ");           
    }

    public static void showLibraryOperationMenu(){
        System.out.println("=== Library Operstion");
        System.out.println("1: Borrow Book");
        System.out.println("2: Return Book");
        System.out.println("3: Display Borrow Book");
        System.out.println("4: Display Available Book");
        System.out.println("5: Display Borrowrecords");
        System.out.println("6: Back");
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();
        MemberManager memberManager = new MemberManager(manager);

      BookManager bookManager = new BookManager(manager, memberManager);
        while(true){
        showMainMenu();

       int choice = sc.nextInt();
      sc.nextLine();

        switch(choice){
            case 1:
            boolean authmenu = true;
          while(authmenu){
                showAuthenticationMenu();

               int authChoice = sc.nextInt();
              sc.nextLine();

               switch(authChoice){
               case 1:
                manager.login();
                break;

               case 2:
                manager.logout();
                break;

               case 3:
                manager.loginStatus();
                break;

               case 4:
               authmenu = false;
                break; // Back
            }
        }
        break;

            case 2:
            boolean employ = true;
            while(employ){
                showEmployeeMenu();
                int employchoice =sc.nextInt();
                sc.nextLine();
                switch (employchoice) {
                    case 1:
                        manager.addUser();
                        break;
                    case 2:
                        manager.searchUser();
                        break;
                    case 3:
                        manager.displayUser();
                        break;
                    case 4:
                        manager.updateUser();
                        break;
                    case 5:
                        manager.changeUsername();
                        break;
                    case 6:
                        manager.deleteUser();
                        break;
                    case 7:
                        employ = false;
                        break;            
                
                    default:
                        break;
                }
            }
            break;
            
            case 3:
             boolean member = true;
             while(member){
                showMemberManagementMenu();
                int memberchoice = sc.nextInt();
                sc.nextLine();
                switch (memberchoice) {
                    case 1:
                        memberManager.addMember();
                        break;
                    case 2:
                        memberManager.searchMember();
                        break;
                    case 3:
                        memberManager.displayMember();
                        break;
                    case 4:
                         member = false;
                         break;
                    default:
                        break;
                }
             }
              break;

            case 4:
             boolean book= true;
             while(book){ 
             showBookManagementMenu();

              int bookchoice =sc.nextInt();
              sc.nextLine();
              switch(bookchoice){
                case 1:
                bookManager.addBook();
                break;
                case 2:
                bookManager.serachBook();
                break;
                case 3:
                bookManager.displayBook();
                break;
                case 4:
                book = false;
                break;

              }
            }
            break;
            case 5:
            boolean library =true;
            while (library) {
            showLibraryOperationMenu();

               int librarycho= sc.nextInt();
               sc.nextLine();
               switch (librarycho) {
                case 1:
                    bookManager.borrowBook();
                    break;
                case 2:
                   bookManager.returnBook();
                break;
                case 3:
                  bookManager.displayBorrowBook();
                break;
                case 4:
                   bookManager.displayAvailableBook();
                break;
                case 5:
                   bookManager.displayBorrowRecords();
                break;
                case 6:
                 library = false;
                 break;
                default:
                    break;
               }

            }
            break;
            case 6:
            return;
    }
    }
}
}