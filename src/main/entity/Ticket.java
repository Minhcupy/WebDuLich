package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private BigDecimal price;
    private Integer quantity;
    
    @Column(name = "ticket_type")
    private String ticketType;
    
    // Foreign Key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
}