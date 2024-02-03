package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);
        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        String msg = g1.greet("스프링");
        String msg2 = g2.greet("스프링2");
        System.out.println(msg);
        System.out.println(msg2);
        System.out.println("g1 == g2 is " + (g1 == g2)); //별개의 설정이 없으면 스프링은 하나의 빈 객체만 생성한다. @Bean 에 대해 하나. --> singleton 범위를 갖는다.
        ctx.close();
    }
}