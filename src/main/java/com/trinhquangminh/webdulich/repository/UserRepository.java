package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByName(String name);
    Optional<Users> findByName(String name);
}
