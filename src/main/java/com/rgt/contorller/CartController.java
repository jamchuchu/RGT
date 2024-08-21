package com.rgt.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rgt.dto.request.CafeTableReqDto;
import com.rgt.dto.request.MenuReqDtoForCart;
import com.rgt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    //장바구니 메뉴 추가
    @PostMapping("/menu")
    public ResponseEntity<?> addMenuToCart(@RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<String, Long> cart;
        try {
           cart = cartService.addMenuToCart(
                   reqDto.getCafeId(),
                   reqDto.getTableNumber(),
                   reqDto.getMenuName(),
                   reqDto.getAddMenuQuantity()
           );
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }


    // 카트에 메뉴 수량 감소 -- 1개씩 감소
    @PatchMapping("/menu")
    public ResponseEntity<?> reduceMenuToCart(@RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<String, Long> cart;
        try {
            cart = cartService.reduceMenuToCart(
                    reqDto.getCafeId(),
                    reqDto.getTableNumber(),
                    reqDto.getMenuName()
            );
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    // 카트에 메뉴 삭제
    @DeleteMapping("/menu")
    public ResponseEntity<?> removeMenuToCart(@RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<String, Long> cart;
        try {
            cart = cartService.removeMenuToCart(
                    reqDto.getCafeId(),
                    reqDto.getTableNumber(),
                    reqDto.getMenuName()
            );
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    //장바구니 조회
    @GetMapping("")
    public ResponseEntity<?> getCart(@RequestBody CafeTableReqDto reqDto) {
        Map<String, Long> cart;
        try {
            cart = cartService.getTableCart(
                    reqDto.getCafeId(),
                    reqDto.getTableNumber()
            );
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }


}
