package hello.core.singleton;

public class StatefulService {

    //싱글톤에선 공유되는 필드를 유지하면 안됨
//    private int price; //상태를 유지하는 필드

    public int order(String name, int price){
        System.out.println("name = " + name + " price = "+price);
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
}
