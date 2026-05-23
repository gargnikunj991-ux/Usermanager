
import java.util.Scanner;

class User {

    private String name;
    private int age;
    private String email;

    public User(String name, int age, String email) {
        this.name = name;
        if (age > 0) {
            this.age = age;
        }
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Name:" + name + ", Age:" + age + ", Email:" + email;
    }

}

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
            System.out.println("6: Exit");
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
                    System.out.println("Data saving");
                    System.out.println("Program is ending...");
                    return;
                default:
                    return;
            }
        }

    }
}
