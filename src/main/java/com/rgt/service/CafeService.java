package com.rgt.service;

import com.rgt.dto.response.MenuRespDto;
import com.rgt.entity.Menu;
import com.rgt.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CafeService {
    private final CafeRepository cafeRepository;

    // 카페 생성(테이블 개수, 메뉴 입력)
    // 테이블 별 카트 생성

    // 메뉴 확인 카페 별
    public List<MenuRespDto> getCafeMenuByCafeId(Long cafeId){
        List<Menu> menus = cafeRepository.findCafeByCafeId(cafeId).getMenus();
        return menus.stream().map(MenuRespDto::from).toList();
    }
}
