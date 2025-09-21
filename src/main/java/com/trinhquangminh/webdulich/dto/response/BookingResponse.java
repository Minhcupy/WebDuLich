package com.trinhquangminh.webdulich.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer tourId;
    private String tourName;
    private String bookingDate;
    private int numberOfPeople;
    private String status;
    private double totalPrice;
}
