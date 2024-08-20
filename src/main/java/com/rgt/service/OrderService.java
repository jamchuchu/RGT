package com.rgt.service;

import com.rgt.repository.CafeRepository;
import com.rgt.repository.OrderDetailRepository;
import com.rgt.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    // 주문 추가 (redis)
    // 주문이 없을 때 추가 가능
    // 주문이 있을 때 추가 불가 안내 -> error

    // 상태변경 완료 or 취소
    // db에 입력

    // 주문 조회 테이블 별로(confirm 상태인것 만) -- redis 참고


}
