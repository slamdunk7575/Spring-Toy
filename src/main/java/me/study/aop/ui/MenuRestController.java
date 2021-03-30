package me.study.aop.ui;

import me.study.aop.application.MenuService;
import me.study.aop.common.ExecutionTime;
import me.study.aop.dto.MenuRequest;
import me.study.aop.dto.MenuResponse;
import me.study.aop.solve1.MenuService1;
import me.study.aop.solve2.MenuService2;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MenuRestController {

    private final MenuService menuService;

    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/api/menus")
    public ResponseEntity<MenuResponse> create(@RequestBody final MenuRequest menuRequest) {
        final MenuResponse created = menuService.createMenu(menuRequest);
        final URI uri = URI.create("/api/menus/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/api/menus")
    public ResponseEntity<List<MenuResponse>> findAll() {
        List<MenuResponse> result = menuService.getMenus();
        return ResponseEntity.ok()
                .body(result);
    }

}
