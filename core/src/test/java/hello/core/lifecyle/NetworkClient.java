package hello.core.lifecyle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    //생성자 호출
    //의존관계 주입
    //객체값 초기화

    private String url;

    public NetworkClient() {
        System.out.println("생성자호출, url = "+url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    public void connect(){
        System.out.println("connect: "+url);
    }

    public void call(String message) {
        System.out.println("call: "+url+" message = "+message);
    }

    //서비스 종료시 호출
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
    public void disconnect() {
        System.out.println("close "+url);
    }
}
