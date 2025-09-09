package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.PaymentRequest;
import com.trinhquangminh.webdulich.dto.response.PaymentResponse;
import com.trinhquangminh.webdulich.model.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "bookingId", target = "bookingId.id")
    Payment toEntity(PaymentRequest request);

    @Mapping(source = "bookingId.id", target = "bookingId")
    PaymentResponse toResponse(Payment payment);

    @Mapping(source = "bookingId", target = "bookingId.id")
    void updatePayment(@MappingTarget Payment payment, PaymentRequest request);
}
