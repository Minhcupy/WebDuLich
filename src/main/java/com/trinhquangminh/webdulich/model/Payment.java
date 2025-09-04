package com.trinhquangminh.webdulich.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking bookingId;
    @Column(name = "payment_date")
    private String paymentDate;
    @Column(name = "amount")
    private double amount;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "status")
    private String status;

}
