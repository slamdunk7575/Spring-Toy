package me.study.di.spring;

import org.springframework.stereotype.Component;

@Component("brazilCoffee")
public class BrazilCoffeeBean implements CoffeeBean {

    @Override
    public String brew() {
        return "브라질 원두에서 커피가 나온다~~~";
    }
}
