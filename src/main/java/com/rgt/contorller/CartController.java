package com.rgt.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rgt.dto.request.CafeTableReqDto;
import com.rgt.dto.request.MenuReqDtoForCart;
import com.rgt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart Management", description = "장바구니 관리 API")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "장바구니에 메뉴 추가", description = "원하는 만큼의 메뉴를 장바구니에 추가합니다.")
    @PostMapping("/menu")
    public ResponseEntity<Map<Long, Long>> addMenuToCart(
            @Parameter(description = "메뉴 추가 요청 정보", required = true)
            @RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
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

    @Operation(summary = "장바구니 메뉴 수량 수정", description = "장바구니에 있는 메뉴의 수량을 수정합니다.")
    @PatchMapping("/menu")
    public ResponseEntity<Map<Long, Long>> modifyMenuToCart(
            @Parameter(description = "메뉴 수정 요청 정보", required = true)
            @RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
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

    @Operation(summary = "장바구니에서 메뉴 삭제", description = "장바구니에서 특정 메뉴를 삭제합니다.")
    @DeleteMapping("/menu")
    public ResponseEntity<Map<Long, Long>> removeMenuToCart(
            @Parameter(description = "메뉴 삭제 요청 정보", required = true)
            @RequestBody MenuReqDtoForCart reqDto) throws JsonProcessingException {
        Map<Long, Long> cart = cartService.removeMenuToCart(
                reqDto.getCafeId(),
                reqDto.getTableNumber(),
                reqDto.getMenuId()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    @Operation(summary = "장바구니 조회", description = "특정 카페의 특정 테이블의 장바구니를 조회합니다.")
    @GetMapping("/cafe/table")
    public ResponseEntity<Map<Long, Long>> getCart(
            @Parameter(description = "카페 ID", required = true)
            @RequestParam(name = "cafeId") Long cafeId,
            @Parameter(description = "테이블 번호", required = true)
            @RequestParam(name = "tableNumber") Long tableNumber) throws JsonProcessingException {

        Map<Long, Long> cart = cartService.getTableCart(cafeId, tableNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }
}