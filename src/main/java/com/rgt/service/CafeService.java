package com.rgt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rgt.constants.ExceptionMessage;
import com.rgt.dto.response.MenuRespDto;
import com.rgt.entity.Cafe;
import com.rgt.entity.Menu;
import com.rgt.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;
    private final CartService cartService ;

    //카페 입력
    public Cafe saveCafe(Long tableCount) throws JsonProcessingException {
        Cafe cafe = new Cafe(0L, tableCount, new ArrayList<>());
        cafe = cafeRepository.save(cafe);
        saveCafeTable(cafe.getCafeId(), tableCount);
        return cafe;
    }

    // 카페 테이블 입력(테이블 개수)
    public void saveCafeTable(Long cafeId, Long tableCount) throws JsonProcessingException {
        Map<Long, Map<Long, Long>> carts = new HashMap<>();

        for(Long tableNumber = 1L; tableNumber <= tableCount ; tableNumber++){
            Map<Long, Long> cart = new HashMap<>();
            carts.put(tableNumber, cart);
        }
        cartService.saveCarts(cafeId, carts);
    }


    // 메뉴 확인 카페 별
    public List<MenuRespDto> getCafeMenuByCafeId(Long cafeId){
        List<Menu> menus = cafeRepository.findCafeByCafeId(cafeId).getMenus();
        if(menus.isEmpty()){
            throw new NotFoundException(ExceptionMessage.NOT_FOUND_ITEM_BY_ID);
        }
        return menus.stream().map(MenuRespDto::from).toList();
    }
}
