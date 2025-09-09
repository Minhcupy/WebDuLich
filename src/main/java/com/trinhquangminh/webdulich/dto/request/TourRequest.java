package com.trinhquangminh.webdulich.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequest {
    private String name;
    private String description;
    private double price;
    private String startDate;
    private String endDate;
    private Integer locationId;   // chỉ gửi id
    private Integer categoryId;   // chỉ gửi id
    private String image;
    private String status;
}
