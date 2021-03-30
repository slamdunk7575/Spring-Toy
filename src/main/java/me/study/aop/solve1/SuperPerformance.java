package me.study.aop.solve1;

import me.study.aop.domain.Menu;
import me.study.aop.dto.MenuRequest;
import me.study.aop.dto.MenuResponse;

import java.util.List;

public abstract class SuperPerformance<T> {

    private long before() {
        return System.currentTimeMillis();
    }

    private void after(long start) {
        long end = System.currentTimeMillis();
        System.out.println("실행시간 : " + (end - start));
    }

    public List<T> getDataAll() {
        long start = before();
        List<T> result = findAll();
        after(start);
        return result;
    }

    public MenuResponse createData(MenuRequest menuRequest) {
        long start = before();
        Menu menu = create(menuRequest);
        after(start);
        return MenuResponse.of(menu);
    }

    public abstract List<T> findAll();
    public abstract Menu create(MenuRequest menuRequest);
}
