package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<Long, UserOrder> {
}
