
import java.util.Scanner;


public class N {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();

        manager.loadData();
        while (true) {

           System.out.println("=== Authentication ===");
           System.out.println("1 Login");
           System.out.println("2 Logout");
           System.out.println("3 Login status ");

           System.out.println("=== Account ===");
           System.out.println("4 Add User");
           System.out.println("5 Update User");
           System.out.println("6 Serach user");
           System.out.println("7 Display users");
           System.out.println("8 Delete user");
           System.out.println("9 Change username");
           System.out.println("10 Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {                
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
                    manager.addUser();
                    break;
                case 5:
                    manager.updateUser();
                    break;
                case 6:
                    manager.searchUser();
                    break;
                case 7:
                    manager.displayUser();
                    break;
                case 8:
                    manager.deleteUser();
                    break;
                case 9:
                    manager.changeUsername();
                    break;
                case 10:
                    System.out.println("Data saving");
                    System.out.println("Program is ending...");
                    return;
                default:
                    return;
            }
        }

    }
}
