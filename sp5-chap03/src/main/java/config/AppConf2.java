package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppConf2 {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao);
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
        return pwdSvc;
    }

    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao, memberPrinter);
    }

    @Bean
    public MemberInfoPrinter infoPrinter(){ //위의 Dependency Injection 과 다른 점은 생성자가 아니라 SETTER를 이용해 DI를 했다는 점.
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao);
        infoPrinter.setPrinter(memberPrinter);
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
