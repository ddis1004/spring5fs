package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {
    @Autowired
    private MemberDao memberDao;

    @GetMapping("/member/{id}")
    public String detail(@PathVariable("id") String memId, Model model){
        Member member = memberDao.selectByEmail(memId);
        if(member == null){
            throw new MemberNotFoundException(memId);
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleNotFoundException(){
        return "member/noMember";
    }
}
