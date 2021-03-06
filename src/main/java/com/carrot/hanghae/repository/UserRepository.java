package com.carrot.hanghae.repository;

import com.carrot.hanghae.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserId(String userId);

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserName(String userName);
}
