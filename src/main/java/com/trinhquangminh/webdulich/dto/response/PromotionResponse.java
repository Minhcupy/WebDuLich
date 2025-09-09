package com.trinhquangminh.webdulich.dto.response;

import lombok.Data;

@Data
public class PromotionResponse {
    private Integer id;
    private String promotionCode;
    private String description;
    private double discountPercentage;
    private String startDate;
    private String endDate;
    private String status;
}
