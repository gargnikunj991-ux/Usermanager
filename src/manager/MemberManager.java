package manager;

import database.DatabaseConnection;
import database.MemberDAO;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Scanner;
import model.Member;

interface MemberOperation {

    void addMember();

    void searchMember();

    void displayMember();
}

interface MemberStorage {

    void saveMember(HashMap<String, Member> members);

    void loadMember(HashMap<String, Member> members);
}
public class MemberManager implements MemberOperation {

    HashMap<String, Member> members = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public boolean memberExists(String memberId) {
        return members.containsKey(memberId);
    }
    private UserManager userManager;

    public MemberManager(UserManager userManager) {
        this.userManager = userManager;
        Connection connection
                = DatabaseConnection.getConnection();

        dao = new MemberDAO(connection);

    }
    private MemberDAO dao;

    public void addMember() {
        userManager.getCurrentUser();
        if (userManager.getCurrentUser() == null) {
            System.out.println("Please login first");
            return;
        }
        System.out.println("Enter the member name");
        String memberName = sc.nextLine();
        while (memberName.isEmpty()) {
            System.out.println("Name is invalid ");
            System.out.println("Enter the name again");
            memberName = sc.nextLine();
        }
        System.out.println("Enter the memberid");
        String memberId = sc.nextLine();
        while (memberId.isEmpty()) {
            System.out.println("MemberId is wrong ");
            memberId = sc.nextLine();
        }
        while (dao.memberExists(memberId)) {
            System.out.println("Member already exist ");
            memberId = sc.nextLine();
        }
        Member member = new Member(memberName, memberId);
        if (dao.addMember(member)) {
              System.out.println("Member is added");
        }else{
            System.out.println("Member could not be added");
        }

    }

    

    public void searchMember() {
        System.out.println("Enter the memberId");
        String memberId = sc.nextLine();
        dao.searchMember(memberId);
    }

    public void displayMember() {
        if(!dao.hasMembers()){
            System.out.println("There are no members in the system");
            return;
        }
        dao.displayMember();
    }
    }

