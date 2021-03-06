package me.study.di;

public class BrazilCoffeeBean implements CoffeeBean {
    @Override
    public void brew() {
        System.out.println("브라질 원두에서 커피가 나온다!!!");
    }
}
