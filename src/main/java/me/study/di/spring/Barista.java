package me.study.di.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Barista {

    private final CoffeeBean coffeeBean;

    public Barista(@Qualifier("colombiaCoffee") CoffeeBean coffeeBean) {
        this.coffeeBean = coffeeBean;
    }

    public String makeCoffee() {
        return "커피를 내린다~~~\n" + coffeeBean.brew();
    }
}
