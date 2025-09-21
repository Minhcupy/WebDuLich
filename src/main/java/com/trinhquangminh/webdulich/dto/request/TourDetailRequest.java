package com.trinhquangminh.webdulich.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDetailRequest {
    private Integer tourId;
    private Integer locationId;
    private String description;
    private String image;
}
