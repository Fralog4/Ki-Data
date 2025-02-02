package com.app.Ki_Data.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    default boolean isAlreadyTaken(String email) {
        return findByEmail(email).isPresent();
    }
}
