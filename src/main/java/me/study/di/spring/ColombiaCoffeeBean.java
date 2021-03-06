package me.study.di.spring;

import org.springframework.stereotype.Component;

@Component("colombiaCoffee")
public class ColombiaCoffeeBean implements CoffeeBean {

    @Override
    public String brew() {
        return "콜롬비아 원두에서 커피가 나온다~~~";
    }
}
