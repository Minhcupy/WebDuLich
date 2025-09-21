package com.trinhquangminh.webdulich.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationRequest {
    private String name;
    private String description;
    private String image;
    private String mainAttraction;
}
