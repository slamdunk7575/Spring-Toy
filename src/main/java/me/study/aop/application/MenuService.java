package me.study.aop.application;

import me.study.aop.common.ExecutionTime;
import me.study.aop.domain.Menu;
import me.study.aop.dto.MenuRequest;
import me.study.aop.dto.MenuResponse;
import me.study.aop.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @ExecutionTime
    @Transactional
    public MenuResponse createMenu(final MenuRequest menuRequest) {
        Menu menu = menuRequest.toMenu();
        Menu savedMenu = menuRepository.save(menu);
        return MenuResponse.of(savedMenu);
    }

    @ExecutionTime
    public List<MenuResponse> getMenus() {
        List<Menu> menus = menuRepository.findAll();
        return MenuResponse.ofList(menus);
    }

}
