package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.TourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDetailRepository extends JpaRepository<TourDetail , Integer> {
    List<TourDetail> findByTourId_Id(Integer tourId);
    List<TourDetail> findByLocationId_Id(Integer locationId);
}
