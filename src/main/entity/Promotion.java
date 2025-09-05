package com.trinhquangminh.webdulich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String description;
    
    @Column(name = "discount_percentage")
    private Double discountPercentage;
    
    @Column(name = "start_date")
    private String startDate;
    
    @Column(name = "end_date")
    private String endDate;
    
    @Column(name = "promotion_code")
    private String promotionCode;
    
    private String status;
}