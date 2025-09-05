package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tour")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    private String description;
    private String image;
    private Double price;
    
    @Column(name = "start_date")
    private String startDate;
    
    @Column(name = "end_date")
    private String endDate;
    
    private String status;
    
    // Foreign Keys
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    
    // Relationships
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TourDetail> tourDetails;
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TourSchedule> tourSchedules;
}