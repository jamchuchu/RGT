package com.rgt.contorller;

import com.rgt.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("")
    public ResponseEntity<?> getMenu(@RequestParam Long menuId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.getMenuDetail(menuId));
    }


}
