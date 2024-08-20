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


    // 매장과 테이블 번호 선택 시 카트 생성 , 카트 번호 리턴

    // 카트에 메뉴 추가

    // 카드 내 메뉴 개수 변경
    // up or down

    // 카트 메뉴 1개 삭제

    // 카트 메뉴 전체 삭제 == 주문 (메뉴 부분 비우기)





}
