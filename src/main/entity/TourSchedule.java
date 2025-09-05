package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tour_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String accommodation;
    private String activity;
    
    @Column(name = "day_come")
    private LocalDateTime dayCome;
    
    @Column(name = "day_go")
    private LocalDateTime dayGo;
    
    private String meal;
    
    // Foreign Key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
}