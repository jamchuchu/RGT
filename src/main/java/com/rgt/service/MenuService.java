package com.rgt.service;

import com.rgt.dto.response.MenuRespDto;
import com.rgt.entity.Cafe;
import com.rgt.entity.Menu;
import com.rgt.repository.CafeRepository;
import com.rgt.repository.MenuRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final EntityManager entityManager;


    //메뉴 상세 정보 확인
    public MenuRespDto getMenuDetail(Long menuId){
        Menu menu = menuRepository.getMenuByMenuId(menuId);
        return MenuRespDto.from(menu);
    }

    public Long getMenuIdByCafeAndMenuName(Long cafeId, String menuName){
        Cafe cafe = entityManager.find(Cafe.class, cafeId);
        return menuRepository.getMenuByCafeAndMenuName(cafe, menuName).getMenuId();
    }
}
