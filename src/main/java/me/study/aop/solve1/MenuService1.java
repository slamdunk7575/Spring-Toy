package me.study.aop.solve1;

import me.study.aop.domain.Menu;
import me.study.aop.dto.MenuRequest;
import me.study.aop.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService1 extends SuperPerformance {

    private final MenuRepository menuRepository;

    public MenuService1(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu create(MenuRequest menuRequest) {
        Menu savedMenu = menuRepository.save(menuRequest.toMenu());
        return savedMenu;
    }
}
