package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.PaymentRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.PaymentResponse;
import com.trinhquangminh.webdulich.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    PaymentService paymentService;

    @PostMapping("/create")
    public ApiResponse<PaymentResponse> create(@RequestBody PaymentRequest request, HttpServletRequest servletRequest) {
        return paymentService.createPayment(request, servletRequest);
    }

    @GetMapping("/vnpay-return")
    public ApiResponse<String> vnPayReturn(@RequestParam Map<String, String> params) {
        return paymentService.handleVNPayReturn(params);
    }
}
