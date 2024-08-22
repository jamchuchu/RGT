package com.rgt.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rgt.constants.OrderState;
import com.rgt.dto.request.CafeTableReqDto;
import com.rgt.dto.request.MenuReqDtoForCart;
import com.rgt.dto.request.OrderReqDto;
import com.rgt.dto.response.OrderRespDto;
import com.rgt.service.CafeService;
import com.rgt.service.CartService;
import com.rgt.service.MenuService;
import com.rgt.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Order Management", description = "주문 관리 API")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;

    @Operation(summary = "카트 내용 전체 주문", description = "카트에 있는 모든 항목을 주문합니다.")
    @PostMapping("/carts")
    public ResponseEntity<OrderRespDto> orderCarts(
            @Parameter(description = "카페 테이블 정보", required = true)
            @RequestBody CafeTableReqDto reqDto) throws JsonProcessingException {
        Map<Long, Long> cart = cartService.getTableCart(reqDto.getCafeId(), reqDto.getTableNumber());
        cartService.resetTableCart(reqDto.getCafeId(), reqDto.getTableNumber());

        OrderReqDto orderReqDto = OrderReqDto.of(
                reqDto.getUserId(),
                reqDto.getCafeId(),
                reqDto.getTableNumber(),
                cart);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.addOrder(orderReqDto));
    }

    @Operation(summary = "테이블별 주문 내용 확인", description = "특정 카페의 특정 테이블의 주문 내용을 확인합니다.")
    @GetMapping("/cafe/table")
    public ResponseEntity<List<OrderRespDto>> getUserOrderWithConfirm(
            @Parameter(description = "카페 테이블 정보", required = true)
            @RequestBody CafeTableReqDto reqDto) {
        List<OrderRespDto> response = orderService.getUserOrderWithConfirm(reqDto.getCafeId(), reqDto.getTableNumber());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "사용자별 주문 내용 확인", description = "특정 사용자의 주문 내용을 확인합니다.")
    @GetMapping("/user")
    public ResponseEntity<List<OrderRespDto>> getUserOrderWithConfirm(
            @Parameter(description = "사용자 ID", required = true)
            @RequestParam(name = "userId") Long userId) {
        List<OrderRespDto> response = orderService.getUserOrderByUserId(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "카페별 주문 내용 확인 (사장 전용)", description = "특정 카페의 모든 주문 내용을 확인합니다.")
    @GetMapping("/cafe")
    public ResponseEntity<List<OrderRespDto>> getUserOrders(
            @Parameter(description = "카페 ID", required = true)
            @RequestParam(name = "cafeId") Long cafeId) {
        List<OrderRespDto> response = orderService.getUserOrderInCafeWithConfirm(cafeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "주문 상태 변경", description = "특정 주문의 상태를 변경합니다.")
    @PatchMapping("/state")
    public ResponseEntity<?> modifyOrderState(
            @Parameter(description = "주문 ID", required = true)
            @RequestParam(name = "orderId") Long orderId,
            @Parameter(description = "변경할 주문 상태", required = true)
            @RequestParam(name = "orderState") OrderState orderState) {
        if(orderState == OrderState.COMPLETE){
            orderService.orderComplete(orderId);
        }else if(orderState == OrderState.CANCEL){
            orderService.orderCancel(orderId);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OK");
    }
}