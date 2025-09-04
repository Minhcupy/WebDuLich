package com.trinhquangminh.webdulich.repository;

import com.trinhquangminh.webdulich.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket , Integer> {
}
