// PaymentRequest.java
package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private Integer bookingId;
    private double amount;
    private String paymentMethod; // VNPay, Momo, ...
}
