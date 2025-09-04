package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourScheduleRepository extends JpaRepository<TourSchedule , Integer> {
}
