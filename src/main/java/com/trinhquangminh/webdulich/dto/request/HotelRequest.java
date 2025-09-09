package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HotelRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    private double rating;
    private String image;
    private LocalDateTime checkIntTime;
    private LocalDateTime checkOutTime;
    private Integer locationId;
}
