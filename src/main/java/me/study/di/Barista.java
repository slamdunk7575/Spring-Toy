package me.study.di;

public class Barista {

    private String name;
    private CoffeeBean coffeeBean;

    public Barista(String name) {
        this.name = name;
    }

    public void makeCoffee() {
        System.out.println("커피를 내린다!!!");
        this.coffeeBean.brew();
    }

    public void setCoffeeBean(CoffeeBean coffeeBean) {
        this.coffeeBean = coffeeBean;
    }
}
