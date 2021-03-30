package me.study.aop.solve2;

import me.study.aop.dto.MenuResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MenuServicePerformance implements MenuService2 {

    private final MenuService2 menuService2;

    public MenuServicePerformance(MenuService2 menuService2) {
        this.menuService2 = menuService2;
    }

    @Override
    public List<MenuResponse> getMenus() {
        long start = before();
        List<MenuResponse> menus = menuService2.getMenus();
        after(start);

        return menus;
    }

    private long before() {
        return System.currentTimeMillis();
    }

    private void after(long start) {
        long end = System.currentTimeMillis();
        System.out.println("실행시간... : " + (end - start));
    }

}
