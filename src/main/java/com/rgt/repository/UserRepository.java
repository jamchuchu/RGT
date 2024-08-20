package com.rgt.repository;

import com.rgt.entity.Cafe;
import com.rgt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {

}
