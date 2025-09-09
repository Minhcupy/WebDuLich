package com.trinhquangminh.webdulich.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private Integer id;
    private Integer userId;
    private Integer tourId;
    private int rating;
    private String comment;
    private String reviewDate;
    private String image;
}
