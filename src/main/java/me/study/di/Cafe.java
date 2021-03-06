package me.study.di;

public class Cafe {
    public static void main(String[] args) {
        Barista barista = new Barista("강백호");

        barista.setCoffeeBean(new BrazilCoffeeBean());
        barista.makeCoffee();

        barista.setCoffeeBean(new ColombiaCoffeeBean());
        barista.makeCoffee();

    }
}
