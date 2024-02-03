package config;

import controller.RegisterRequestValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasename("message.label"); //message 패키지의 label.properties에서 불러오겠다
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    /*@Override
    public Validator getValidator(){
        return new RegisterRequestValidator(); //global 범위 validator 설정
        //RegisterRequestValidator는 RegisterRequest만 validate 하므로 사실 global valdiator로 적합하지 않음.
    }*/

}
