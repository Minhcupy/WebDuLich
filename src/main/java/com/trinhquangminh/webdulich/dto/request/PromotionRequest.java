package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;

@Data
public class PromotionRequest {
    private String promotionCode;
    private String description;
    private double discountPercentage;
    private String startDate;
    private String endDate;
    private String status;
}
