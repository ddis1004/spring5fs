package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class RestMemberController {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberRegisterService memberRegisterService;

    @GetMapping("/api/members")
    public List<Member> members(){
        return memberDao.selectAll();
    }

    @GetMapping("/api/members/{id}")
    public Member member(@PathVariable Long id,
                         HttpServletResponse response) throws IOException{
        Member member = memberDao.selectById(id);
        if(member == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return member;
    }

    @PostMapping("/api/members")
    public void newMember(@RequestBody@Valid RegisterRequest regReq,
                          HttpServletResponse response) throws IOException {
        try{
            Long newMemberId = memberRegisterService.regist(regReq);
            response.setHeader("Location", "/api/members/" + newMemberId);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (DuplicateMemberException e){
            response.sendError(HttpServletResponse.SC_CONFLICT);
        }
    }

}
