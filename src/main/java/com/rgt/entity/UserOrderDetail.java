package com.rgt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Builder
@Entity
@Table(name = "user_order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userOrderDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_order_id")
    private UserOrder userOrder;

    private Long menuId;
    private Long menuQuantity;

    public static UserOrderDetail of(UserOrder userOrder, Long menuId, Long menuQuantity) {
        return UserOrderDetail.builder()
                .userOrder(userOrder)
                .menuId(menuId)
                .menuQuantity(menuQuantity)
                .build();
    }

}