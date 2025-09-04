package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.TourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourDetailRepository extends JpaRepository<TourDetail , Integer> {
}
