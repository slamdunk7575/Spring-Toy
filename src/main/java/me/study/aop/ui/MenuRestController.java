package me.study.aop.ui;

import me.study.aop.application.MenuService;
import me.study.aop.common.ExecutionTime;
import me.study.aop.dto.MenuRequest;
import me.study.aop.dto.MenuResponse;
import org.springframework.http.ResponseEntity;
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

    @ExecutionTime
    @PostMapping("/api/menus")
    public ResponseEntity<MenuResponse> create(@RequestBody final MenuRequest menuRequest) {
        final MenuResponse created = menuService.create(menuRequest);
        final URI uri = URI.create("/api/menus/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @ExecutionTime
    @GetMapping("/api/menus")
    public ResponseEntity<List<MenuResponse>> findAll() {
        return ResponseEntity.ok()
                .body(menuService.findAll());
    }
}
