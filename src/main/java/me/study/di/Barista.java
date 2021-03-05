package me.study.di;

public class Barista {

    private String name;
    private BrazilCoffeeBean brazilCoffeeBean;

    public Barista(String name) {
        this.name = name;
        this.brazilCoffeeBean = new BrazilCoffeeBean();
    }

    public void makeCoffee() {
        System.out.println("커피를 내린다!!!");
        this.brazilCoffeeBean.brew();
    }
}
