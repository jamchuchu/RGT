package com.rgt.contorller;

import com.rgt.dto.response.MenuRespDto;
import com.rgt.service.CafeService;
import com.rgt.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final CafeService cafeService;

    //카페 별 메뉴 확인
    @GetMapping("/{cafeId}")
    public ResponseEntity<List<MenuRespDto>> getCafeMenuByCafeId(@PathVariable("cafeId") Long cafeId) {
        List<MenuRespDto> menus = cafeService.getCafeMenuByCafeId(cafeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menus);
    }

    //메뉴 단품 확인
    @GetMapping("/")
    public ResponseEntity<MenuRespDto> getMenu(@RequestParam(name = "menuId") Long menuId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.getMenuDetail(menuId));
    }


}
