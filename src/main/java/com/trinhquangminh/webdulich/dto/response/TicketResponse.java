package com.trinhquangminh.webdulich.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TicketResponse {
    private Integer id;
    private Integer bookingId;
    private String ticketType;
    private BigDecimal price;
    private int quantity;
}
