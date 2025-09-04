package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Integer> {
}
