package com.rgt.dto.response;

import com.rgt.constants.OrderState;
import com.rgt.entity.UserOrder;
import com.rgt.entity.UserOrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRespDto {
    private Long userId;
    private Long userOrderId;
    private Long cafeId;
    private Long tableNumber;
    private OrderState orderState;
    private Map<Long, Long> orderDetails; // menuId, menuQuantity

    public static OrderRespDto from(UserOrder userOrder){
        return OrderRespDto.builder()
                .userId(userOrder.getUser().getUserId())
                .userOrderId(userOrder.getUserOrderId())
                .cafeId(userOrder.getCafeId())
                .tableNumber(userOrder.getTableNumber())
                .orderState(userOrder.getOrderState())
                .orderDetails(userOrder.getOrderDetails().stream()
                        .collect(Collectors.toMap(
                                UserOrderDetail::getMenuId,
                                UserOrderDetail::getMenuQuantity
                        )))
                .build();
    }
}