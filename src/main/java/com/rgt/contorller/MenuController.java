package com.rgt.contorller;

import com.rgt.dto.response.MenuRespDto;
import com.rgt.service.CafeService;
import com.rgt.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Tag(name = "Menu Management", description = "메뉴 관리 API")
public class MenuController {
    private final MenuService menuService;
    private final CafeService cafeService;

    @Operation(summary = "카페별 메뉴 조회", description = "특정 카페의 모든 메뉴를 조회합니다.")
    @GetMapping("/{cafeId}")
    public ResponseEntity<List<MenuRespDto>> getCafeMenuByCafeId(
            @Parameter(description = "카페 ID", required = true)
            @PathVariable("cafeId") Long cafeId) {
        List<MenuRespDto> menus = cafeService.getCafeMenuByCafeId(cafeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menus);
    }

    @Operation(summary = "메뉴 단품 조회", description = "특정 메뉴의 상세 정보를 조회합니다.")
    @GetMapping("/")
    public ResponseEntity<MenuRespDto> getMenu(
            @Parameter(description = "메뉴 ID", required = true)
            @RequestParam(name = "menuId") Long menuId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.getMenuDetail(menuId));
    }
}