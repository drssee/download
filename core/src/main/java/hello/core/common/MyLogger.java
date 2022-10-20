package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS) //가짜를 만들어둠?
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"] "+"["+requestURL+"] "+message);
    }

    @PostConstruct //빈 생성후 기본 초기화 끝나고 사용가능한 상태
    public void init(){
        uuid= UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean created:"+this);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+uuid+"] request scope bean closed:"+this);
    }

}
