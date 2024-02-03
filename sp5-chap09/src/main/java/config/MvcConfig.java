package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc //spring mvc를 위한 설정을 대신 해주는 annotation
public class MvcConfig implements WebMvcConfigurer { 

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp"); //jsp를 이용해 컨트롤러의 실행 결과를 보여주기 위한 설정을 추가한다
        //jsp 파일을 view 구현으로 사용할수 있도록 등록
    }
}
