package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

    private ChangePasswordService changePasswordService;

    @GetMapping
    public String getChangePwd(@ModelAttribute("changePwdCommand") ChangePwdCommand changePwdCommand, HttpSession session){
        /*
        if(session == null){
            return "redirect:/login";
        }*/
        return "edit/changePwdForm";
    }

    @PostMapping
    public String postChangePwd(@Valid ChangePwdCommand changePwdCommand, Errors errors, HttpSession session){
        if(errors.hasErrors()){
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try{
            changePasswordService.changePassword(authInfo.getEmail(),
                    changePwdCommand.getCurrentPassword(),
                    changePwdCommand.getNewPassword(),
                    changePwdCommand.getConfirmNewPassword());
        } catch (WrongIdPasswordException e){
            errors.rejectValue("currentPassword", "notMatching");
            return "edit/changePwdForm";
        } catch (ConfirmPasswordException e){
            errors.rejectValue("confirmPassword", "notMatching");
            return "edit/changePwdForm";
        }
        return "redirect:/main";

    }

    public void setChangePasswordService(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new ChangePwdCommandValidator()); //controller단위의 validator 설정
    }

}
