package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location , Integer> {
    Page<Location> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
