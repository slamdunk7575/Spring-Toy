package me.study.aop.dto;

import me.study.aop.domain.Menu;

import java.math.BigDecimal;

public class MenuRequest {
    private String name;
    private BigDecimal price;

    public MenuRequest() {
    }

    public MenuRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Menu toMenu() {
        return new Menu(name, price);
    }

}
