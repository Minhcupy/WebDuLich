package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.TicketRequest;
import com.trinhquangminh.webdulich.dto.response.TicketResponse;
import com.trinhquangminh.webdulich.model.Booking;
import com.trinhquangminh.webdulich.model.Ticket;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "bookingId", target = "booking_Id.id")
    Ticket toEntity(TicketRequest request);

    @Mapping(source = "booking_Id.id", target = "bookingId")
    TicketResponse toResponse(Ticket ticket);

    @Mapping(source = "bookingId", target = "booking_Id.id")
    void updateTicket(@MappingTarget Ticket ticket, TicketRequest request);
}
