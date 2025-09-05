package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String image;
    private Double rating;
    
    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;
    
    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;
    
    // Foreign Key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}