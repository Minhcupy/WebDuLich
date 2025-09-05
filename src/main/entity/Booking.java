package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "booking_date")
    private String bookingDate;
    
    @Column(name = "number_of_people")
    private Integer numberOfPeople;
    
    private String status;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    // Foreign Keys
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
    
    // Relationships
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;
    
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}