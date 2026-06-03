import java.util.HashMap;
import java.util.Scanner;
interface MemberOperation{
    void addMember();
    void searchMember();
    void displayMember();
}

public class MemberManager implements  MemberOperation{
    HashMap<String, Member> members = new HashMap<>();
    Scanner sc = new Scanner(System.in);
   public boolean memberExists(String memberId){
    return members.containsKey(memberId);
}
   public void addMember(){
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
     System.out.println("Member is added succesfully ");
    
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
