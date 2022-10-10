package com.example.demo;

public class test{
    public static int a = 1;

    public static void main(String[] args) {
        test2 t2 = new test2();
        test3 t3 = new test3();
        System.out.println("t2.a"+t2.a);
        System.out.println("t3.a"+t3.a);
        System.out.println("t3에서 변경함");
        t3.a=5;
        System.out.println("t2.a"+t2.a);
        System.out.println("t3.a"+t3.a);
        System.out.println("t2에서 변경함");
        t2.a=10;
        System.out.println("t2.a"+t2.a);
        System.out.println("t3.a"+t3.a);

    }
}
