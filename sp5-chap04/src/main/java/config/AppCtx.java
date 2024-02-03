package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService pwdSvc = new ChangePasswordService();
        //pwdSvc.setMemberDao(memberDao()); 이젠 Autowired를 넣었기 때문에 set하지 않아도 자동으로 주입됨
        return pwdSvc;
    }

    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter();
    }

    @Bean
    public MemberInfoPrinter infoPrinter(){ //위의 Dependency Injection 과 다른 점은 생성자가 아니라 SETTER를 이용해 DI를 했다는 점.
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        //infoPrinter.setMemberDao(memberDao());
        //infoPrinter.setPrinter(memberPrinter()); //setter에 Autowired를 붙여 자동으로 DI
        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
