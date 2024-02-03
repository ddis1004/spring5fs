package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@Import(AppConf2.class)
/*import 를 통해 AppConf2를 가져올경우 AppConfigContext 생성시 알아서 AppConf2까지 가져와서 컨테이너 초기화
* 즉 최상위 Config에 import를 넣으면 Config 생성 코드를 수정할 필요가 없다 */
public class AppConfImport {

    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
}
