package com.rgt.service;

import com.rgt.repository.CafeRepository;
import com.rgt.repository.CartDetailRepository;
import com.rgt.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;


}
