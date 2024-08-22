package com.rgt.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rgt.dto.response.MenuRespDto;
import com.rgt.entity.Cafe;
import com.rgt.repository.CafeRepository;
import com.rgt.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/cafe")
@RequiredArgsConstructor
@Tag(name = "Cafe Management", description = "카페 관리 API")
public class CafeController {
    private final CafeService cafeService;

    @Operation(summary = "카페 등록", description = "새로운 카페를 등록합니다.")
    @PostMapping("")
    public ResponseEntity<Cafe> saveCafe(
            @Parameter(description = "등록할 카페의 테이블 수", required = true)
            @RequestParam(name = "cafeNumber") Long cafeNumber
    ) throws JsonProcessingException {
        Cafe savedCafe = cafeService.saveCafe(cafeNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCafe);
    }
}