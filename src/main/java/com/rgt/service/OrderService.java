package com.rgt.service;

import com.rgt.constants.OrderState;
import com.rgt.dto.response.OrderRespDto;
import com.rgt.dto.request.OrderReqDto;
import com.rgt.entity.Menu;
import com.rgt.entity.User;
import com.rgt.entity.UserOrder;
import com.rgt.repository.OrderDetailRepository;
import com.rgt.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;


    // 주문 추가
    public OrderRespDto saveOrder(OrderReqDto reqDto){
        User user = entityManager.find(User.class, reqDto.getUserId());
        UserOrder responseOrder = orderRepository.save(UserOrder.from(user, reqDto));
        return OrderRespDto.from(responseOrder);
    }


    // 현재 confirm인 주문 있나?
    public boolean existConfirmOrder(Long cafeId, Long tableNumber){
        return orderRepository.existsByCafeIdAndTableNumberAndOrderState(
                cafeId,
                tableNumber,
                OrderState.CONFIRM
        );
    }

    // 주문이 없을 때 추가 가능
    // 주문이 있을 때 추가 불가 안내 -> er-
    // 'ror
    public OrderRespDto addOrder(OrderReqDto reqDto){
        if(existConfirmOrder(reqDto.getCafeId(), reqDto.getTableNumber())){
            //error
        }
        return saveOrder(reqDto);
    }


    // 상태변경 완료 or 취소 // confirm 에서만 변경 가능
    @Transactional
    public void orderComplete(Long orderId){
        UserOrder order = entityManager.find(UserOrder.class, orderId);
        if(order.getOrderState() != OrderState.CONFIRM){
            //error
        }
        order.setOrderState(OrderState.COMPLETE);
    }

    public void orderCancel(Long orderId){
        UserOrder order = entityManager.find(UserOrder.class, orderId);
        if(order.getOrderState() != OrderState.CONFIRM){
            //error
        }
        order.setOrderState(OrderState.CANCEL);
    }


    //주문 조회 by userId
    public List<OrderRespDto> getUserOrderByUserId(Long userId) {
        List<UserOrder> orders = orderRepository.getUserOrdersByUserUserId(userId);
        return orders.stream().map(OrderRespDto::from).toList();
    }

    // 주문 조회 테이블 별로(confirm 상태인것 만)
    public List<OrderRespDto> getUserOrderWithConfirm(Long cafeId, Long tableNumber) {
        List<UserOrder> orders = orderRepository.getUserOrdersByCafeIdAndTableNumberAndOrderState(
                cafeId,
                tableNumber,
                OrderState.CONFIRM
        );
        return orders.stream().map(OrderRespDto::from).toList();
    }

    //주문조회 전체
    public List<OrderRespDto> getUserOrderInCafeWithConfirm(Long cafeId){
        List<UserOrder> orders = orderRepository.getUserOrdersByCafeIdAndOrderState(
                cafeId,
                OrderState.CONFIRM
        );
        return orders.stream().map(OrderRespDto::from).toList();
    }


}
