package me.study.aop.application;

import me.study.aop.dto.MenuRequest;
import me.study.aop.dto.MenuResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @DisplayName("까페 메뉴를 생성할 수 있다.")
    @Test
    void create_menu() {
        // given
        MenuRequest menuRequest = new MenuRequest("아이스 아메리카노", new BigDecimal(4000));

        // when
        MenuResponse menuResponse = menuService.createMenu(menuRequest);

        // then
        assertThat(menuResponse.getId()).isNotNull();
        assertThat(menuResponse.getName()).isEqualTo("아이스 아메리카노");
        assertThat(menuResponse.getPrice()).isEqualByComparingTo(new BigDecimal(4000));
    }

    @DisplayName("까페 메뉴 생성시 가격은 필수 정보이다.")
    @Test
    void require_price() {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            menuService.createMenu(new MenuRequest("아이스 까페라떼", null));
        }).withMessageMatching("가격은 0원 이상이어야 합니다.");
    }

    @DisplayName("까페 가격은 0원 이상이어야 한다.")
    @Test
    void price_not_zero() {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            menuService.createMenu(new MenuRequest("아이스 까페라떼", BigDecimal.valueOf(-1000)));
        }).withMessageMatching("가격은 0원 이상이어야 합니다.");

    }

    @DisplayName("까페 목록을 조회할 수 있다.")
    @Test
    void find_all_menus() {
        // given
        MenuRequest menuRequest = new MenuRequest("아이스 아메리카노", new BigDecimal(4000));
        MenuResponse menuResponse = menuService.createMenu(menuRequest);

        // when
        List<MenuResponse> results = menuService.getMenus();

        // then
        assertThat(results).isNotEmpty();
        assertThat(results.stream()
                .map(MenuResponse::getName)
                .collect(Collectors.toList())).containsAll(Arrays.asList("아이스 아메리카노"));
    }


}