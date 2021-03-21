package me.study.aop.application;

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

    @Transactional
    public MenuResponse create(final MenuRequest menuRequest) {
        Menu menu = menuRequest.toMenu();
        return MenuResponse.of(menuRepository.save(menu));
    }

    public List<MenuResponse> findAll() {
        return MenuResponse.ofList(menuRepository.findAll());
    }

}
