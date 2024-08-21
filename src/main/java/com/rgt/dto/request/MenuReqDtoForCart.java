package com.rgt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuReqDtoForCart {
    private Long cafeId;
    private Long tableNumber;

    private Long menuId;
    private Long menuQuantity;
}
