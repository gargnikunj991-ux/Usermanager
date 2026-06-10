package model; 
public class Member{
    private String memberName;
    private String memberId;

    public Member(String memberName, String memberId) {
        this.memberName = memberName;
        this.memberId = memberId;
    }

    public String getMemberName(){
        return memberName;
    }
    public void setMemberName(String memberName){
        this.memberName =memberName;
    }
    public String getMemberId(){
        return memberId;
    }
    public void setMemberId(String memberId){
        this.memberId = memberId;
    } 
    public String toString(){
        return "MemberName:"+memberName+" MemberId:"+memberId;
    }
}
