package com.rgt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_order_detail")
@Getter
@Setter
public class UserOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userOrderDetailId;


    private Long userOrderId;

    private Long menuId;
    private Long menuQuantity;


}