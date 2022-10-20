package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    //prototype은 요청이 왔을때 초기화->의존관계주입후 반환하고 더이상 관리x 그러므로 종료 메서드는 실행할수 없음

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("find bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);
        bean1.destroy();
        bean2.destroy();
        ac.close();
    }
    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            //생성 의존관계주입 초기화후 사용전에
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
