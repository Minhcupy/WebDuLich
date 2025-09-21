package com.trinhquangminh.webdulich.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String mainAttraction;
}
