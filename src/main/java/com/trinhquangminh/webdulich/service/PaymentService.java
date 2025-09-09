package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.config.VNPayConfig;
import com.trinhquangminh.webdulich.dto.request.PaymentRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.PaymentResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.PaymentMapper;
import com.trinhquangminh.webdulich.model.Booking;
import com.trinhquangminh.webdulich.model.Payment;
import com.trinhquangminh.webdulich.repository.BookingRepository;
import com.trinhquangminh.webdulich.repository.PaymentRepository;
import com.trinhquangminh.webdulich.utils.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {

    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;
    PaymentMapper paymentMapper;
    VNPayConfig vnPayConfig;

    public ApiResponse<PaymentResponse> createPayment(PaymentRequest request, HttpServletRequest servletRequest) {
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        Payment payment = paymentMapper.toEntity(request);
        payment.setBookingId(booking);
        payment.setStatus("PENDING");

        payment = paymentRepository.save(payment);

        String paymentUrl = VNPayUtil.createPaymentUrl(payment, vnPayConfig, servletRequest);
        PaymentResponse response = paymentMapper.toResponse(payment);
        response.setPaymentUrl(paymentUrl);

        return ApiResponse.<PaymentResponse>builder()
                .result(response)
                .build();
    }

    public ApiResponse<String> handleVNPayReturn(Map<String, String> params) {
        boolean valid = VNPayUtil.validateSignature(params, vnPayConfig.getSecretKey());
        if (!valid) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Integer paymentId = Integer.parseInt(params.get("vnp_TxnRef"));
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));

        String responseCode = params.get("vnp_ResponseCode");
        if ("00".equals(responseCode)) {
            payment.setStatus("SUCCESS");
        } else {
            payment.setStatus("FAILED");
        }
        paymentRepository.save(payment);

        return ApiResponse.<String>builder()
                .result(payment.getStatus())
                .build();
    }
}
