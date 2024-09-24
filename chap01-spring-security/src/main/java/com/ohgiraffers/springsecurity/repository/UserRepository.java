package com.ohgiraffers.springsecurity.repository;

import com.ohgiraffers.springsecurity.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
}
