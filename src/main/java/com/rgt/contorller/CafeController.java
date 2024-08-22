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

import java.util.List;

@RestController
@RequestMapping("/api/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    //카페 등록
    @PostMapping("")
    public ResponseEntity<Cafe> saveCafe(@RequestParam(name = "cafeNumber") Long cafeNumber) throws JsonProcessingException {
        Cafe savedCafe = cafeService.saveCafe(cafeNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCafe);
    }






}
