package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.Client;
import spring.Client2;

@Configuration
public class AppCtx {

    @Bean
    public Client client(){
        Client client = new Client();
        client.setHost("THE HOST");
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2(){
        Client2 client = new Client2();
        //client.connect() 이렇게 직접 initialize해주는 방법도 가능하다.
        client.setHost("THE HOST2");
        return client;
    }
}
