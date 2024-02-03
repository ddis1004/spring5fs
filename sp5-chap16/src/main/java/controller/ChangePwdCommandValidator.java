package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.ChangePwdCommand;

public class ChangePwdCommandValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePwdCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePwdCommand changePwdCommand = (ChangePwdCommand) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "confirmNewPassword", "required");
        if(!changePwdCommand.isConfirmPwdEqualToNewPwd()){
            errors.rejectValue("confirmNewPassword", "nomatch");
        }
    }
}
