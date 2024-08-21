package com.rgt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgt.repository.CafeRepository;
import com.rgt.repository.CartDetailRepository;
import com.rgt.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;



    //매장과 table 생성 시 생성
    public Map<Long, Map<String, Long>> saveCarts(Long cafeId, Map<Long, Map<String, Long>> carts) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = objectMapper.writeValueAsString(carts);

        redisTemplate.opsForValue().set(key, value);
        return getCarts(cafeId);
    }


    //매장 내 테이블 별 카트 모두 들고오기
    public Map<Long, Map<String, Long>> getCarts(Long cafeId) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = redisTemplate.opsForValue().get(key); // Redis에서 JSON 문자열 가져오기

        if (value == null) {
            //error 발생
        }
        return objectMapper.readValue(value, Map.class); // JSON 문자열을 맵으로 변환하여 반환
    }

    public Map<String, Long> saveTableCart(Long cafeId, Long tableNum,  Map<String, Long> cart) throws JsonProcessingException {
        Map<Long, Map<String, Long>> carts = getCarts(cafeId);
        carts.put(tableNum, cart);

        String key = cafeId.toString();
        String value = objectMapper.writeValueAsString(carts);

        redisTemplate.opsForValue().set(key, value);
        return getTableCart(cafeId, tableNum);
    }

    //테이블 별 카트 들고오기
    // 매장과 테이블 선택하면 카트 배정 -- 처음 저장
    public Map<String, Long> getTableCart(Long cafeId, Long tableNum) throws JsonProcessingException {
        Map<Long, Map<String, Long>> carts = getCarts(cafeId);
        Map<String, Long> cart = carts.getOrDefault(tableNum, null);
        if(cart == null){
            //error
        }
        return carts.get(tableNum);
    }

    // 카트에 메뉴 수량 추가
    public Map<String, Long> addMenuToCart(Long cafeId, Long tableNum, String menuName) throws JsonProcessingException {
        Map<String, Long> cart = getTableCart(cafeId, tableNum);
        Long menuQuantity = cart.get(menuName) == null? 0L : cart.get(menuName);
        cart.put(menuName, menuQuantity+1);
        saveTableCart(cafeId, tableNum, cart);
        return getTableCart(cafeId, tableNum);
    }

    // 카트에 메뉴 수량 감소
    public Map<String, Long> reduceMenuToCart(Long cafeId, Long tableNum, String menuName) throws JsonProcessingException {
        Map<String, Long> cart = getTableCart(cafeId, tableNum);
        Long menuQuantity = cart.get(menuName) == null? 0L : cart.get(menuName);
        if(menuQuantity == 0L){
            //error 발생
        }
        cart.put(menuName, menuQuantity-1);
        saveTableCart(cafeId, tableNum, cart);
        return getTableCart(cafeId, tableNum);
    }

    // 카트 메뉴 1개 삭제
    public Map<String, Long> removeMenuToCart(Long cafeId, Long tableNum, String menuName) throws JsonProcessingException {
        Map<String, Long> cart = getTableCart(cafeId, tableNum);
        try {
            cart.remove(menuName);
        }catch (Exception e){
            // error 발생
        }
        saveTableCart(cafeId, tableNum, cart);
        return getTableCart(cafeId, tableNum);
    }

    // 카트 메뉴 전체 삭제 == 주문 (메뉴 부분 비우기)
    public Map<String, Long> resetTableCart(Long cafeId, Long tableNum) throws JsonProcessingException {
        saveTableCart(cafeId, tableNum, new HashMap<>());
        return getTableCart(cafeId, tableNum);
    }





}
