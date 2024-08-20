package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<CafeTable, Long> {
}
