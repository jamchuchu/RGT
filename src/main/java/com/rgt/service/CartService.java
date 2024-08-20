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

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final RedisService redisService;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;



    //매장과 table 생성 시 생성
    public void saveCarts(Long cafeId, Map<Long, Map<String, Long>> carts) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = objectMapper.writeValueAsString(carts);

        redisTemplate.opsForValue().set(key, value);
    }

    //매장 내 테이블 별 카트 모두 들고오기
    public Map<Long, Map<String, Long>> getCarts(Long cafeId) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = redisTemplate.opsForValue().get(key); // Redis에서 JSON 문자열 가져오기

        if (value != null) {
            return objectMapper.readValue(value, Map.class); // JSON 문자열을 맵으로 변환하여 반환
        } else {
            return null;
        }
    }

    //테이블 별 카트 들고오기
    public Map<String, Long> getTableCart(Long cafeId, Long tableNum) throws JsonProcessingException {
        String key = cafeId.toString();
        String value = redisTemplate.opsForValue().get(key); // Redis에서 JSON 문자열 가져오기

        if (value != null) {
            // 정확한 타입 정보를 제공하여 JSON 문자열을 Map으로 변환
            Map<Long, Map<String, Long>> carts = objectMapper.readValue(value, new TypeReference<Map<Long, Map<String, Long>>>(){});
            return carts.get(tableNum);
        } else {
            return null;
        }
    }



    // 매장과 테이블 선택하면 카트 배정 -- 처음 저장


    // 카트에 메뉴 추가

    // 카드 내 메뉴 개수 변경
    // up or down

    // 카트 메뉴 1개 삭제

    // 카트 메뉴 전체 삭제 == 주문 (메뉴 부분 비우기)





}
