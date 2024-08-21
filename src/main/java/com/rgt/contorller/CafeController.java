package com.rgt.contorller;

import com.rgt.dto.response.MenuRespDto;
import com.rgt.repository.CafeRepository;
import com.rgt.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    //카페 별 메뉴 확인
    @GetMapping("/menu/{cafeId}")
    public ResponseEntity<List<MenuRespDto>> getCafeMenuByCafeId(@PathVariable("cafeId") Long cafeId) {
        List<MenuRespDto> menus = cafeService.getCafeMenuByCafeId(cafeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menus);
    }



}
