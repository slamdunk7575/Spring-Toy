package me.study.aop.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Price price;

    protected Menu() {
    }

    public Menu(String name, BigDecimal price) {
        validateName(name);
        this.name = name;
        this.price = new Price(price);
    }

    private void validateName(String name) {
        if(Objects.isNull(name)) {
            throw new IllegalArgumentException("메뉴 이름은 필수 정보입니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.value();
    }
}
