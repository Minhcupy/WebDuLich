package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
