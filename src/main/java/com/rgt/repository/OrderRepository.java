package com.rgt.repository;

import com.rgt.constants.OrderState;
import com.rgt.entity.Cafe;
import com.rgt.entity.UserOrder;
import com.rgt.entity.UserOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    boolean existsByCafeIdAndTableNumberAndOrderState(
            Long cafeId,
            Long tableNumber,
            OrderState orderState
    );
    List<UserOrder> getUserOrdersByCafeIdAndTableNumberAndOrderState(
            Long cafeId,
            Long tableNumber,
            OrderState orderState
    );

    List<UserOrder> getUserOrdersByCafeIdAndOrderState(
            Long cafeId,
            OrderState orderState);

    List<UserOrder> getUserOrdersByUserUserId(Long userId);

}
