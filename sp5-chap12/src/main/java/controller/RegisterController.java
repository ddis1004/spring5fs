package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private MemberRegisterService memberRegSvc;

    @RequestMapping("/register/step1")
    public String handleStep1(){
        return "register/step1";
    }

    @PostMapping("/register/step2")
    public String handleStep2(
            @RequestParam(value="agree", defaultValue = "false") Boolean agree, Model model
    ) {
       if(!agree){
           return "register/step1";
       }
       model.addAttribute("registerRequest", new RegisterRequest());//
       return "register/step2";
    }

    @GetMapping("register/step2")
    public String handleStep2Get(){
        return "redirect:/register/step1";
    }

    @PostMapping("/register/step3")
    public String handleStep3(@Valid RegisterRequest request, Errors errors){
        //new RegisterRequestValidator().validate(request, errors);
        //@Valid annotation이 글로벌 validator로 validation을 수행
        if(errors.hasErrors())
            return "register/step2";
        try{
            memberRegSvc.regist(request);
            return "register/step3";
        }catch(DuplicateMemberException exception){
            errors.rejectValue("email", "duplicate");
            return "register/step2";
        }
    }

    /*@InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new RegisterRequestValidator()); //controller단위의 validator 설정
    }*/



}
