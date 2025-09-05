package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tour_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String description;
    private String image;
    
    // Foreign Keys
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
}