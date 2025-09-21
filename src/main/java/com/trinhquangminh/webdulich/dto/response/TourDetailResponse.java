package com.trinhquangminh.webdulich.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDetailResponse {
    private Integer id;
    private String description;
    private String image;

    private String tourName;     // lấy từ Tour
    private String locationName; // lấy từ Location
}
