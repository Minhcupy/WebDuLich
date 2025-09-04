package com.trinhquangminh.webdulich.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tour_Schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tourId;
    @Column(name = "day_come")
    private Date dayCome;
    @Column(name = "day_go")
    private Date daygo;
    @Column(name = "activity")
    private String activity;
    @Column(name = "meal")
    private String meal;
    @Column(name = "accommodation")
    private String accommodation;
}
