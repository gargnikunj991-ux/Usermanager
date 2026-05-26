
import java.util.Scanner;


public class N {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();

        manager.loadData();
        while (true) {

            System.out.println("1: Add user");
            System.out.println("2: Display user");
            System.out.println("3: Delete user");
            System.out.println("4: Serach user");
            System.out.println("5: Update user email");
            System.out.println("6: Change your username");
            System.out.println("7: Login");
            System.out.println("8: Logout the user");   
            System.out.println("9: Check login status"); 
            System.out.println("10: Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    manager.addUser();
                    break;
                case 4:
                    manager.searchUser();
                    break;
                case 2:
                    manager.displayUser();
                    break;
                case 3:
                    manager.deleteUser();
                    break;

                case 5:
                    manager.updateUser();
                    break;
                case 6:
                    manager.changeUsername();
                    break;
                case 7:
                manager.login();
                break;
                case 8:
                manager.logout();
                break;
                case 9:
                manager.loginStatus();
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
