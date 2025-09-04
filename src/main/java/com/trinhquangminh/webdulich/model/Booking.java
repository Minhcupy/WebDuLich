package com.trinhquangminh.webdulich.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users userId;
    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tourId;
    @Column(name = "booking_date")
    private String bookingDate;
    @Column(name = "number_of_people")
    private int numberOfPeople;
    @Column(name = "status")
    private String status;
    @Column(name = "total_price")
    private double totalPrice;


}
