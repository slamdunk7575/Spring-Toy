package me.study.aop.solve2;

import me.study.aop.dto.MenuResponse;
import me.study.aop.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService2Impl implements MenuService2 {

    private final MenuRepository menuRepository;

    public MenuService2Impl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuResponse> getMenus() {
        return MenuResponse.ofList(menuRepository.findAll());
    }

}
