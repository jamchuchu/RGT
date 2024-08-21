package com.rgt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgt.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;



    //매장과 table 생성 시 생성
    public Map<Long, Map<Long, Long>> saveCarts(Long cafeId, Map<Long, Map<Long, Long>> carts) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = objectMapper.writeValueAsString(carts);

        redisTemplate.opsForValue().set(key, value);
        return getCarts(cafeId);
    }


    //매장 내 테이블 별 카트 모두 들고오기
    public Map<Long, Map<Long, Long>> getCarts(Long cafeId) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = redisTemplate.opsForValue().get(key); // Redis에서 JSON 문자열 가져오기

        if (value == null) {
            //error 발생
        }
        return objectMapper.readValue(value, new TypeReference<Map<Long, Map<Long, Long>>>() {});
    }

    public Map<Long, Long> saveTableCart(Long cafeId, Long tableNum,  Map<Long, Long> cart) throws JsonProcessingException {
        Map<Long, Map<Long, Long>> carts = getCarts(cafeId);
        carts.put(tableNum, cart);

        String key = cafeId.toString();
        String value = objectMapper.writeValueAsString(carts);

        redisTemplate.opsForValue().set(key, value);
        return getTableCart(cafeId, tableNum);
    }

    //테이블 별 카트 들고오기
    // 매장과 테이블 선택하면 카트 배정 -- 처음 저장
    public Map<Long, Long> getTableCart(Long cafeId, Long tableNum) throws JsonProcessingException {
        Map<Long, Map<Long, Long>> carts = getCarts(cafeId);
        Map<Long, Long> cart = carts.getOrDefault(tableNum, null);

        if(cart == null){
//            //error
        }
        return carts.get(tableNum);
    }

    // 카트에 메뉴 수량 추가
    public Map<Long, Long> addMenuToCart(Long cafeId, Long tableNum, Long menuId, Long addMenuQuantity) throws JsonProcessingException {
        Map<Long, Long> cart = getTableCart(cafeId, tableNum);
        Long menuQuantity = cart.get(menuId) == null? 0L : cart.get(menuId);
        cart.put(menuId,menuQuantity + addMenuQuantity);
        return saveTableCart(cafeId, tableNum, cart);
    }



    // 카트에 메뉴 수량 변경
    public Map<Long, Long> modifyMenuToCart(Long cafeId, Long tableNum, Long menuId, Long quantity) throws JsonProcessingException {
        Map<Long, Long> cart = getTableCart(cafeId, tableNum);
        Optional.ofNullable(cart.get(menuId))
                .orElseThrow(() -> new RuntimeException("//error"));
        cart.put(menuId, quantity);
        return saveTableCart(cafeId, tableNum, cart);
    }


    // 카트 메뉴 1개 삭제
    public Map<Long, Long> removeMenuToCart(Long cafeId, Long tableNum, Long menuId) throws JsonProcessingException {
        Map<Long, Long> cart = getTableCart(cafeId, tableNum);
        try {
            cart.remove(menuId);
        }catch (Exception e){
            // error 발생
        }
        return saveTableCart(cafeId, tableNum, cart);
    }

    // 카트 메뉴 전체 삭제 == 주문 (메뉴 부분 비우기)
    public Map<Long, Long> resetTableCart(Long cafeId, Long tableNum) throws JsonProcessingException {
        return saveTableCart(cafeId, tableNum, new HashMap<>());
    }





}
