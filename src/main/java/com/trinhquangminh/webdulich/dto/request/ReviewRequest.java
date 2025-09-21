package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Integer userId;
    private Integer tourId;
    private int rating;
    private String comment;
    private String reviewDate;
    private String image;
}
