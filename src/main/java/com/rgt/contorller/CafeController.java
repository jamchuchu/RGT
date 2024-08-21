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

    //카페 등록
    @PostMapping("")
    public ResponseEntity<?> saveCafe (@RequestParam(name = "cafeNumber") Long cafeNumber){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cafeService.saveCafe(cafeNumber));
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }






}
