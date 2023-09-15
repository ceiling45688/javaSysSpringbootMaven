package com.ceiling45688.repository;


import com.ceiling45688.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // Spring 提供基本CRUD方法的实现

    Boolean existsByEmail(String email);
    User findByEmail(String email);
}
