package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int userA = bean1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        int userB = bean2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        int price = userA;
        System.out.println("price = " + price);

//        Assertions.assertThat(bean1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}