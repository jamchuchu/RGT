package com.rgt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderReqDto {
    private Long cafeId;
    private Long tableNumber;
    private Map<Long, Long> orderDetails; //id, quantity

    public static OrderReqDto of(Long cafeId, Long tableNumber, Map<Long, Long> orderDetails){
        return OrderReqDto.builder()
                .cafeId(cafeId)
                .tableNumber(tableNumber)
                .orderDetails(orderDetails)
                .build();
    }

}
