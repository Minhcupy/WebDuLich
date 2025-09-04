package com.trinhquangminh.webdulich.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tour_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tourId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location locationId;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
}
