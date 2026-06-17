package manager;
import database.DatabaseConnection;
import database.UserDAO;
import java.sql.Connection;
import java.util.Scanner;
import model.User;
   
interface UserOperation {

    void addUser();

    void searchUser();

    void displayUser();

  /*   void updateUser();

    void deleteUser();    

 

    void changeUsername();
*/
}

public class UserManager implements UserOperation {

    Scanner sc = new Scanner(System.in);
    private User currentUser;
    public User getCurrentUser(){
        return currentUser;
    }
    public boolean userExistsPublic(String name) {
    return userExists(name);
}
    public UserManager(){
        Connection connection
                = DatabaseConnection.getConnection();

        dao = new UserDAO(connection);
    }
    private UserDAO dao;

    private boolean userExists(String name) {

        return dao.existUser(name);
    }

    private boolean isValidEmail(String email) {

        return email.contains("@");
    }

    private boolean isValidAge(int age) {

        return age > 17;
    }

    private boolean isWeakPassword(String password) {
        return password.length() < 8;
    }

     private boolean isLoggedIn(){
      if (currentUser ==null) {
        System.out.println("Please login first ");
        return false;
      }else{
        return true;
      }
     }

   

    public void login() {
        if (currentUser ==null) {
           System.out.println("Enter username");
           String username = sc.nextLine();

          System.out.println("Enter password");
          String password = sc.nextLine();

         User user = dao.loginUser(username, password);

            if(user != null) {

            currentUser = user;

            System.out.println(
            "Welcome " + currentUser.getName()
            );

            System.out.println(
            "Login successful"
            );

        } else {

      System.out.println(
        "Wrong username or password"
      );
    }
        } else {
            System.out.println(currentUser.getName() + " is already loggin");
        }
    }

    public void logout() {
        if (!isLoggedIn()) {
            System.out.println("No user is logged in");
        } else {
            System.out.println(currentUser.getName()+" is logout");
            currentUser = null;
        }

    }

    public void loginStatus() {
        if (currentUser == null) {
            System.out.println("No user logged in");
        } else {
            System.out.println(currentUser.getName() + " is  logged in");
        }
    }

    public void addUser() {
        System.out.println("Enter the username");
        String name = sc.nextLine();
        while (name.isEmpty()) {
            System.out.println("Name is wrong");
            System.out.println("Enter your name again");
            name = sc.nextLine();
        }
        if (userExists(name)) {
            System.out.println("User already exist");

        } else {
            System.out.println("Enter your age");
            int age = sc.nextInt();
            sc.nextLine();
            while (!isValidAge(age)) {
                System.out.println("you are not old enought");
                System.out.println("Enter the age");
                age = sc.nextInt();
                sc.nextLine();
            }
            System.out.println("Enter your email");
            String email = sc.nextLine();
            while (!isValidEmail(email)) {
                System.out.println("Invalid email");
                System.out.println("Reenter the email");
                email = sc.nextLine();
            }
            System.out.println("Enter your password");
            String password = sc.nextLine();
            while (isWeakPassword(password)) {
                System.out.println("Passwor is to short");
                password = sc.nextLine();
            }
            User u1 = new User(name, age, email, password);
            if (dao.addUser(u1)) {
            System.out.println("User is resgister");    
            }else{
                System.out.println("User not resgister");
            }
            
        }
    }

    public void searchUser() {
        if (!isLoggedIn()) {
            return;
        }
        System.out.println("Enter username to serach");
        String serach = sc.nextLine();
        dao.searchUser(serach);

       

}

    public void displayUser() {
        if(!dao.hasUser()){
            System.out.println("There is no User in the system");
            return;
        }
        dao.displayUser();
    }

 /*    public void deleteUser() {
        if (!isLoggedIn()) {
            return;
        } else {
            if (users.containsKey(currentUser.getName())) {
                users.remove(currentUser.getName());
                storage.saveUsers(users);
                currentUser = null;
                System.out.println("Account is deleted");
            } else {
                System.out.println("User not found ");
            }
        }
    }

    public void updateUser() {
      if(!isLoggedIn()) {
      }else{
        if (users.containsKey(currentUser.getName())) {
            System.out.println("Enter your email ");
            String newemail = sc.nextLine();
            while (!isValidEmail(newemail)) {
                System.out.println("Invalid email");
                System.out.println("Reenter the email");
                newemail = sc.nextLine();
            }
            User user = users.get(currentUser.getName());
            user.setEmail(newemail);
            System.out.println("Enter your age");
            int newage = sc.nextInt();
            sc.nextLine();
            while (!isValidAge(newage)) {
                System.out.println("Age  is invalid");
                System.out.println("Enter age");
                newage = sc.nextInt();
                sc.nextLine();
            }
            user.setAge(newage);
            storage.saveUsers(users);
            System.out.println("email and age is updated ");
        } else {
            System.out.println("User is not found ");
        }
    }
    }
    public void changeUsername() {
        if (!isLoggedIn()) {
            return;
        }else{
        if (userExists(currentUser.getName())) {
            System.out.println("Enter the new username");
            String newusername = sc.nextLine();
            if (userExists(newusername)) {
                System.out.println("Username already exist ");
            } else {
                User user = users.remove(currentUser.getName());
                user.setName(newusername);
                currentUser = user;
                users.put(newusername, user);
                System.out.println("Username is updated");
                storage.saveUsers(users);
            }
        }
    }
        */
}
