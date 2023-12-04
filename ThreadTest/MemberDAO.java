package ThreadTest;

import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private static List<Member> memberList = new ArrayList<>();

    public void registerMember(Member member){
        memberList.add(member);
    }

    public List getMember(){
        return memberList;
    }
}
