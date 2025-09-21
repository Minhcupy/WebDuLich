package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.BookingRequest;
import com.trinhquangminh.webdulich.dto.response.BookingResponse;
import com.trinhquangminh.webdulich.model.Booking;
import com.trinhquangminh.webdulich.model.Tour;
import com.trinhquangminh.webdulich.model.Users;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "userId", source = "userId.id")
    @Mapping(target = "tourId", source = "tourId.id")
    BookingResponse toResponse(Booking booking);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "tourId", ignore = true)
    Booking toEntity(BookingRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "tourId", ignore = true)
    void updateBooking(@MappingTarget Booking booking, BookingRequest request);

    // Hỗ trợ map entity sang id
    default Integer map(Users user) {
        return user != null ? user.getId() : null;
    }

    default Integer map(Tour tour) {
        return tour != null ? tour.getId() : null;
    }
}

