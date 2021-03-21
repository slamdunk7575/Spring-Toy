package me.study.aop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuTest {

    @DisplayName("까페 메뉴를 생성할 수 있다.")
    @Test
    void create_menu() {
        // given
        String name = "아이스 아메리카노";
        BigDecimal price = BigDecimal.valueOf(4000);

        // when
        Menu menu = new Menu(name, price);

        // then
        assertThat(menu).isNotNull();
    }

    @DisplayName("까페 메뉴의 이름과 가격은 필수정보이다.")
    @Test
    void require_name_and_price() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Menu("아이스 아메리카노", null));
        assertThrows(IllegalArgumentException.class, () -> new Menu(null, BigDecimal.valueOf(4000)));
    }

}