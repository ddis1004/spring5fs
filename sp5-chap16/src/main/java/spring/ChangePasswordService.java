package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

	@Autowired
	private MemberDao memberDao;

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd, String newPwdConfirm) {
		Member member = memberDao.selectByEmail(email);
		if (member == null){
			throw new MemberNotFoundException("Member Not Found");
		}
		if(!oldPwd.equals(member.getPassword())){
			throw new WrongIdPasswordException();
		}
		if(!newPwd.equals(newPwdConfirm)){
			throw new ConfirmPasswordException();
		}
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
