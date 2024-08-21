package com.rgt.dto.resquest;

import com.rgt.constants.OrderState;
import com.rgt.entity.UserOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderReqDto {
    private Long cafeId;
    private Long tableNumber;
    private Map<Long, Long> orderDetails; //id, quantity

}
