package com.rgt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartRespDto {
    private Long cafeId;
    private Long tableNumber;

    private Map<String, Long> menus;

    public static CartRespDto of(Long cafeId, Long tableNumber, Map<String, Long> menus){
        return CartRespDto.builder()
                .cafeId(cafeId)
                .tableNumber(tableNumber)
                .menus(menus)
                .build();
    }
}
