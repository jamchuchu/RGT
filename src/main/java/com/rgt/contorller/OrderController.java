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
    private final MenuService menuService;

    // 카트 내용 전체 주문
    @PostMapping("/carts")
    public ResponseEntity<?> orderCarts(@RequestBody CafeTableReqDto reqDto) throws JsonProcessingException {
        try {
            //table 별 카트 들고오기
            Map<String, Long> cart = cartService.getTableCart(reqDto.getCafeId(), reqDto.getTableNumber());

            //메뉴 이름 -> menuId
            Map<Long, Long> orderDetails = new HashMap<>();
            for(Map.Entry<String, Long> entry : cart.entrySet()){
                Long key = menuService.getMenuIdByCafeAndMenuName(reqDto.getCafeId(), entry.getKey());
                orderDetails.put(key, entry.getValue());
            }

            OrderReqDto orderReqDto = OrderReqDto.of(
                    reqDto.getCafeId(),
                    reqDto.getTableNumber(),
                    orderDetails);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(orderService.saveOrder(orderReqDto));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }

    }

    // 주문 내용 확인하기
    @GetMapping("/cafe/table")
    public ResponseEntity<?> getUserOrderWithConfirm(@RequestBody CafeTableReqDto reqDto) {
        try {
            List<OrderRespDto> response = orderService.getUserOrderWithConfirm(reqDto.getCafeId(), reqDto.getTableNumber());
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
    public ResponseEntity<?> getUserOrders(@RequestParam Long cafeId) {
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
    public ResponseEntity<?> modifyOrderState(@RequestParam Long orderId, OrderState orderState) {
        try {
            if(orderState == OrderState.COMPLETE){
                orderService.orderComplete(orderId);
            }else if(orderState == OrderState.CANCEL){
                orderService.orderCancel(orderId);
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("OK");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }

    }


}
