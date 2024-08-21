package com.rgt.entity;

import com.rgt.constants.OrderState;
import com.rgt.dto.request.OrderReqDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Table(name = "user_order")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userOrderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long cafeId;
    private Long tableNumber;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrderDetail> orderDetails;

    public void setOrderDetails(List<UserOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static UserOrder from(User user, OrderReqDto reqDto) {
        UserOrder userOrder = UserOrder.builder()
                .user(user)
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

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}