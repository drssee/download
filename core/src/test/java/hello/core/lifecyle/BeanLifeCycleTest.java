package hello.core.lifecyle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    //스프링컨테이너등록 -> 스프링빈등록 ->의존관계주입 // -> 초기화+작업 시작 -> 종료시점 // ->스프링빈삭제 ->스프링컨테이너종료
    @Test
    public void lifeCycleTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean("networkClient", NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig {
//        @Bean(initMethod = "init",destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
