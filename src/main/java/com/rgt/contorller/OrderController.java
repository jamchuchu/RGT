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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;

    // 카트 내용 전체 주문
    @PostMapping("/carts")
    public ResponseEntity<?> orderCarts(@RequestBody CafeTableReqDto reqDto) throws JsonProcessingException {
            //table 별 카트 들고오기
            Map<Long, Long> cart = cartService.getTableCart(reqDto.getCafeId(), reqDto.getTableNumber());
            if(cart.isEmpty()){
                //error
            }
            cartService.resetTableCart(reqDto.getCafeId(), reqDto.getTableNumber());

            OrderReqDto orderReqDto = OrderReqDto.of(
                    reqDto.getUserId(),
                    reqDto.getCafeId(),
                    reqDto.getTableNumber(),
                    cart);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.saveOrder(orderReqDto));
    }

    // 주문 내용 확인하기
    @GetMapping("/cafe/table")
    public ResponseEntity<?> getUserOrderWithConfirm(@RequestBody CafeTableReqDto reqDto) {
        List<OrderRespDto> response = orderService.getUserOrderWithConfirm(reqDto.getCafeId(), reqDto.getTableNumber());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // 주문 내용 확인하기
    @GetMapping("/user")
    public ResponseEntity<?> getUserOrderWithConfirm(@RequestParam(name = "userId") Long userId) {
        try {
            List<OrderRespDto> response = orderService.getUserOrderByUserId(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    // 사장 전용
    // 가게 주문 내용 확인하기
    @GetMapping("/cafe")
    public ResponseEntity<?> getUserOrders(@RequestParam(name = "cafeId") Long cafeId) {
        try {
            List<OrderRespDto> response = orderService.getUserOrderInCafeWithConfirm(cafeId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }

    }

    //주문 상태 변경하기
    @PatchMapping("/state")
    public ResponseEntity<?> modifyOrderState(
            @RequestParam(name = "orderId") Long orderId,
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
