package config;

import controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberRegisterService;
import survey.SurveyController;

@Configuration
public class ControllerConfig {

    @Autowired
    private MemberRegisterService memberRegisterService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ChangePasswordService changePasswordService;

    @Bean
    public RegisterController registerController(){
        return new RegisterController();
    }

    @Bean
    public SurveyController surveyController(){
        return new SurveyController();
    }

    @Bean
    public LoginController loginController(){
        LoginController controller = new LoginController();
        controller.setAuthService(authService);
        return controller;
    }

    @Bean
    public LogoutController logoutController(){return new LogoutController();}

    @Bean
    public MainController mainController(){
        return new MainController();
    }

    @Bean
    public ChangePwdController changePwdController(){
        ChangePwdController controller = new ChangePwdController();
        controller.setChangePasswordService(changePasswordService);
        return controller;
    }

    @Bean
    public MemberListController memberListController(){
        return new MemberListController();
    }

    @Bean
    public MemberDetailController memberDetailController(){
        return new MemberDetailController();
    }
}
