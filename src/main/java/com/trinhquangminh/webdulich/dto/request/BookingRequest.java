package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;

@Data
public class BookingRequest {
    private Integer userId;
    private Integer tourId;
    private String bookingDate;
    private int numberOfPeople;
    private String status;
    private double totalPrice;
}
