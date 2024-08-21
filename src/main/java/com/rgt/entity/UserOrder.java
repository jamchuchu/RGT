package com.rgt.entity;

import com.rgt.constants.OrderState;
import com.rgt.dto.resquest.OrderReqDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userOrderId;

    private Long cafeId;
    private Long tableNumber;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrderDetail> orderDetails;

    public static UserOrder from(OrderReqDto reqDto) {
        UserOrder userOrder = UserOrder.builder()
                .cafeId(reqDto.getCafeId())
                .tableNumber(reqDto.getTableNumber())
                .orderState(OrderState.CONFIRM)
                .orderDetails(new ArrayList<>())
                .build();

        List<UserOrderDetail> details = reqDto.getOrderDetails().entrySet().stream()
                .map(entry -> UserOrderDetail.of(userOrder, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        userOrder.setOrderDetails(details);

        return userOrder;
    }
}