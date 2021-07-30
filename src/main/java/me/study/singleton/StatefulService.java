package me.study.singleton;

public class StatefulService {

    /**
     * 상태를 유지하는 필드
     * -> 싱글톤 객체는 상태를 유지(Stateful)하게 설계하면 안된다!
     */
    // private int price;

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; //==> 여기가 문제

        //==> 필드 대신 자바에서 공유되지 않는 '지역변수, 파라미터, ThreadLocal' 등을 사용해야 한다.
        return price;
    }

    /*public int getPrice() {
        return price;
    }*/
}
