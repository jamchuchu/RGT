package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Long, Cart> {
}
