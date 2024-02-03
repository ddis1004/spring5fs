package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.*;

@Configuration
@EnableTransactionManagement
public class AppCtx {

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource());
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

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(60000 * 3);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }
}
