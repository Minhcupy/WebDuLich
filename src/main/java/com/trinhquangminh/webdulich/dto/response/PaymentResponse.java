// PaymentResponse.java
package com.trinhquangminh.webdulich.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Integer id;
    private Integer bookingId;
    private double amount;
    private String paymentMethod;
    private String status;
    private String paymentDate;
    private String paymentUrl; // link thanh toán trả về từ VNPay
}
