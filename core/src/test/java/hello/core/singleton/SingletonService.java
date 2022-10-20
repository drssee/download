package hello.core.singleton;

public class SingletonService {

    //static이라 프로그램이 시작되고 jvm이 static영역에 자동으로 하나 생성해둠
    private static final SingletonService instance = new SingletonService();

    private SingletonService(){}

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
