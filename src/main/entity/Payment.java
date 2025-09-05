package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Double amount;
    
    @Column(name = "payment_date")
    private String paymentDate;
    
    @Column(name = "payment_method")
    private String paymentMethod;
    
    private String status;
    
    // Foreign Key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
}