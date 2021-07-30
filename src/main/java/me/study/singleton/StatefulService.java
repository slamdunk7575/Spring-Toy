package me.study.singleton;

public class StatefulService {

    /**
     * 상태를 유지하는 필드
     * -> 싱글톤 객체는 상태를 유지(Stateful)하게 설계하면 안된다!
     */
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //==> 여기가 문제
    }

    public int getPrice() {
        return price;
    }
}
