package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Component
public class ChangePasswordService {

    @Autowired
    private MemberDao memberDao;

    @Transactional(rollbackFor = SQLException.class) //no rollback for 이라는것도 존재
    public void changePassword(String email, String oldPwd, String newPwd){
        Member member = memberDao.selectByEmail(email);
        if(member == null){
            throw new MemberNotFoundException("change password user not found: " + email);
        }
        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao){
        this.memberDao = memberDao;
    }
}
