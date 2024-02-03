package config;

import controller.RegisterController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import survey.SurveyController;

@Configuration
public class ControllerConfig {

    @Bean
    public RegisterController registerController(){
        return new RegisterController();
    }

    @Bean
    public SurveyController surveyController(){
        return new SurveyController();
    }
}
