
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

interface UserStorage {

    void saveUsers(HashMap<String, User> users);

    void loadUsers(HashMap<String, User> users);
}

class FileStorage implements UserStorage {

    public void saveUsers(HashMap<String, User> users) {
        try {
            FileWriter writer = new FileWriter("user.txt");
            for (User user : users.values()) {
                writer.write(user.getName() + "," + user.getAge() + "," + user.getEmail()+","+user.getPassword());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File cannot update");
        }

    }

    public void loadUsers(HashMap<String, User> users) {
        try {
            File file = new File("user.txt");

            if (!file.exists()) {

                return;
            }
            BufferedReader reader
                    = new BufferedReader(new FileReader("user.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                int age = Integer.parseInt(data[1].trim());
                String email = data[2].trim();
                String   password = data[3].trim();
                User u1 = new User(name, age, email, password);
                users.put(name, u1);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("error");
        }

    }

}

interface UserOperation {

    void addUser();

    void deleteUser();

    void displayUser();

    void updateUser();

    void searchUser();
    void  changeUsername();

}

public class UserManager implements UserOperation {

    HashMap<String, User> users = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    UserStorage storage = new FileStorage();
    private  User currentUser;
    private boolean userExists(String name) {

        return users.containsKey(name);
    }

    private boolean isValidEmail(String email) {

        return email.contains("@");
    }

    private boolean isValidAge(int age) {

        return age > 15;
    }
    private  boolean  isWeakPassword(String password){
        return password.length()<8;
    }
    public void loadData(){
        storage.loadUsers(users);
    }   
    public  void login(){
        if (currentUser== null) {
        System.out.println("Enter username ");
        String username= sc.nextLine();
        if (userExists(username)) {
             System.out.println("Enter password");
             String password = sc.nextLine();
             User user = users.get(username);
               if (user.getPassword().equals(password)) {
                 currentUser = user;
                System.out.println("Welcome "+ currentUser.getName());
                System.out.println("login successful");
               }else{
                System.out.println("Wrong password");
               }
        }else{
            System.out.println("User not found ");
            return;
        }
    }else{
        System.out.println(currentUser.getName()+" is already loggin");
    }
}
    public void logout(){    
        if ( currentUser == null) {
          System.out.println("No user is logged in");
        }else{
            currentUser = null;
            System.out.println("User is logout");  
        }
         
    }
    public void loginStatus(){
        if (currentUser==null) {
            System.out.println("No user logged in");
        }else{
            System.out.println(currentUser.getName()+" is  logged in");
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
            String password= sc.nextLine();
            while (isWeakPassword(password)) { 
                System.out.println("Passwor is to short");
                password =sc.nextLine();
            }
            User u1 = new User(name, age, email,password);
            users.put(name, u1);
            storage.saveUsers(users);
            System.out.println("User is resgister");
        }
    }

    public void searchUser() {
        System.out.println("Enter username to serach");
        String serach = sc.nextLine();

        if (users.containsKey(serach)) {
            User user = users.get(serach);
            System.out.println("user is found ");
            System.out.println(user);
        } else {
            System.out.println("User not found ");
        }
    }

    public void displayUser() {

        if (users.isEmpty()) {
            System.out.println("No user available");
        } else {
            for (User user : users.values()) {

                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge());
                System.out.println("Email: " + user.getEmail());
            }
        }
    }

    public void deleteUser() {
        System.out.println("Enter the username");
        String remove = sc.nextLine();
        if (users.containsKey(remove)) {
            users.remove(remove);
            storage.saveUsers(users);
            System.out.println("User is deleted");
        } else {
            System.out.println("User not found ");
        }
    }

    public void updateUser() {
        System.out.println("Enter the username");
        String update = sc.nextLine();
        if (users.containsKey(update)) {
            System.out.println("Enter your email ");
            String newemail = sc.nextLine();
            while(!isValidEmail(newemail)){
                System.out.println("Invalid email");
                System.out.println("Reenter the email");
                newemail = sc.nextLine();
            }
            User user = users.get(update);
            user.setEmail(newemail);
            System.out.println("Enter your age");
            int newage = sc.nextInt();
            sc.nextLine();
            while(!isValidAge(newage)){
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
    public void changeUsername(){
       System.out.println("Enter the username");
       String username = sc.nextLine();
       if(userExists(username)){
        System.out.println("Enter the new username");
        String newusername =sc.nextLine();
        if(userExists(newusername)){
           System.out.println("Username already exist "); 
        }else{
        User user = users.remove(username);
        user.setName(newusername);
        users.put(newusername, user);
        System.out.println("Username is updated");
        storage.saveUsers(users);
        }
       }
    }
}
