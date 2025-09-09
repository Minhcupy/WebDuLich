package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {
    boolean existsByNameIgnoreCase(String name);
    Page<Tour> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
