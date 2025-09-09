package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TicketRequest {
    private Integer bookingId;
    private String ticketType;
    private BigDecimal price;
    private int quantity;
}
