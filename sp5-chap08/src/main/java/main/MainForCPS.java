package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForCPS {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);

        try {
            cps.changePassword("0508044412TEST@test.net", "0508044412", "1111");
            System.out.println("암호를 변경했습니다");
        } catch(MemberNotFoundException e){
            System.out.println("회원데이터가 존재하지 않습니다");
        } catch(WrongIdPasswordException e){
            System.out.println("암호가 올바르지 않습니다");
        }

        ctx.close();
    }
}
