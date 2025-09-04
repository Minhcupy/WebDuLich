package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion , Integer> {
}
