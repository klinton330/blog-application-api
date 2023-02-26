package com.blogapplication.repository;

import com.blogapplication.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users>findByEmail(String email);

    Optional<Users>findByUsernameOrEmail(String email,String username);

    Optional<Users>findByUsername(String userName);

    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);
}
