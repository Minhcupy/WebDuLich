package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Integer> {
    List<Booking> findByUserId_Id(Integer userId);
    List<Booking> findByTourId_Id(Integer tourId);
    List<Booking> findByStatus(String status);
}
