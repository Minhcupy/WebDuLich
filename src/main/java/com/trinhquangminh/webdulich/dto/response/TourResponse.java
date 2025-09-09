package com.trinhquangminh.webdulich.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourResponse {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String startDate;
    private String endDate;
    private String status;
    private String image;

    private Integer categoryId;
    private String categoryName;

    private Integer locationId;
    private String locationName;
}
