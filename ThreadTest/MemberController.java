package ThreadTest;

import java.util.List;

public class MemberController {
    public static MemberDAO memberDAO = new MemberDAO();
    public synchronized void registerMember(Member member){
        memberDAO.registerMember(member);
    }
    public List getMember(){
        return memberDAO.getMember();
    }
}
