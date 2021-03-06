package me.study.di;

public class ColombiaCoffeeBean implements CoffeeBean {
    @Override
    public void brew() {
        System.out.println("콜롬비아 원두에서 커피가 나온다!!!");
    }
}
