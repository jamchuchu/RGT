package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.UserOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<UserOrderDetail, Long> {
}
