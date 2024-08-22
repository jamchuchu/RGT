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

    //장바구니 메뉴 원하는 만큼 추가
    @PostMapping("/menu")
    public ResponseEntity<Map<Long, Long>> addMenuToCart(@RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<Long, Long> cart = cartService.addMenuToCart(
               reqDto.getCafeId(),
               reqDto.getTableNumber(),
               reqDto.getMenuId(),
               reqDto.getMenuQuantity()
       );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }


    // 카트에 메뉴 수량 수정
    @PatchMapping("/menu")
    public ResponseEntity<Map<Long, Long>> modifyMenuToCart(@RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<Long, Long> cart = cartService.modifyMenuToCart(
                reqDto.getCafeId(),
                reqDto.getTableNumber(),
                reqDto.getMenuId(),
                reqDto.getMenuQuantity()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    // 카트에 메뉴 삭제
    @DeleteMapping("/menu")
    public ResponseEntity<Map<Long, Long>> removeMenuToCart(@RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<Long, Long> cart = cartService.removeMenuToCart(
                    reqDto.getCafeId(),
                    reqDto.getTableNumber(),
                    reqDto.getMenuId()
            );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    //장바구니 조회
    @GetMapping("/cafe/table")
    public ResponseEntity<Map<Long, Long>> getCart(
            @RequestParam(name = "cafeId") Long cafeId,
            @RequestParam(name = "tableNumber") Long tableNumber)throws JsonProcessingException {

        Map<Long, Long> cart = cartService.getTableCart(cafeId, tableNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }


}
