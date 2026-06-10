package manager;
import model.Member;
import java.util.HashMap;
import java.util.Scanner;
import  java.io.*;


interface MemberOperation{
    void addMember();
    void searchMember();
    void displayMember();
}
interface MemberStorage {

    void saveMember(HashMap<String, Member> members);

    void loadMember(HashMap<String, Member> members);
}


class MemberFileStorage implements MemberStorage {

    public void saveMember(HashMap<String, Member> members) {
        try {
            FileWriter writer = new FileWriter("member.txt");
            for (Member member : members.values()) {
                writer.write(member.getMemberName() + "," + member.getMemberId());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File cannot update");
        }

    }

    public void loadMember(HashMap<String, Member > members) {
        try {
            File file = new File("member.txt");

            if (!file.exists()) {

                return;
            }
            BufferedReader reader
                    = new BufferedReader(new FileReader("member.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String memberName = data[0].trim();
                String memberId = data[1].trim();
                Member member = new Member(memberName, memberId);
                members.put(memberId,member);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("error");
        }

    }

}

public class MemberManager implements  MemberOperation{
    HashMap<String, Member> members = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    MemberStorage storage = new MemberFileStorage();
   public boolean memberExists(String memberId){
    return members.containsKey(memberId);
}
private UserManager userManager;

   public void addMember(){
     userManager.getCurrentUser();
    if(userManager.getCurrentUser() == null){
      System.out.println("Please login first");
      return;
    }
     System.out.println("Enter the member name");
     String memberName = sc.nextLine();
     while(memberName.isEmpty()){
        System.out.println("Name is invalid ");
        System.out.println("Enter the name again");
        memberName =sc.nextLine();
    }
     System.out.println("Enter the memberid");
     String memberId = sc.nextLine();
     while(memberId.isEmpty()){
        System.out.println("MemberId is wrong ");
        memberId =sc.nextLine();
     }
     while(members.containsKey(memberId)){
        System.out.println("Member already exist ");
        memberId =sc.nextLine();
     }
     Member  member = new Member(memberName, memberId);
     members.put(memberId, member);
     storage.saveMember(members);   
     System.out.println("Member is added succesfully ");
    
}  
public void loadData(){
    storage.loadMember(members);
}
   public void searchMember(){
    System.out.println("Enter the memberId");
    String memberId = sc.nextLine();
    if (members.containsKey(memberId)) {
        Member member = members.get(memberId);
        System.out.println(member);
        
    }else{
        System.out.println("Member not found ");
    }
 }
   public void displayMember(){
    if(members.isEmpty()){
        System.out.println("NO member found ");
    }else{
        for(Member member :members.values()){
            System.out.println("Name of the member "+member.getMemberName());
            System.out.println("MemberId of the member "+member.getMemberId());
        }
    }
 }
}
