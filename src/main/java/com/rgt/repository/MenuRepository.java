package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu getMenuByMenuId(Long menuId);
    Menu getMenuByCafeAndMenuName(Cafe cafe, String menuName);
}
