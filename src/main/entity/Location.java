package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private String description;
    private String image;
    
    @Column(name = "main_atraction")
    private String mainAttraction;
    
    // Relationships
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tour> tours;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Hotel> hotels;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TourDetail> tourDetails;
}