package com.rgt.contorller;

import com.rgt.dto.response.MenuRespDto;
import com.rgt.repository.CafeRepository;
import com.rgt.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/menu/{cafeId}")
    public ResponseEntity<List<MenuRespDto>> getCafeMenuByCafeId(@PathVariable("cafeId") Long cafeId) {
        List<MenuRespDto> menus = cafeService.getCafeMenuByCafeId(cafeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menus);
    }

//    @GetMapping("/")
//    public ResponseEntity<ApiResponse<?>> a() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(ApiResponse.success(SuccessType.SUCCESS));
//    }

}
