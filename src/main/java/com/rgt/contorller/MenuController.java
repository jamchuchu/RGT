package com.rgt.contorller;

import com.rgt.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

}
