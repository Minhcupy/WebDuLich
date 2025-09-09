package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {
    boolean existsByNameIgnoreCase(String name);
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
