package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> getMenuByMenuId(Long menuId);
    Optional<Menu> getMenuByCafeAndMenuName(Cafe cafe, String menuName);
}
