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


}
