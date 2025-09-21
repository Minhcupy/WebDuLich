package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.HotelRequest;
import com.trinhquangminh.webdulich.dto.response.HotelResponse;
import com.trinhquangminh.webdulich.model.Hotel;
import com.trinhquangminh.webdulich.model.Location;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    Hotel toEntity(HotelRequest request);

    @Mapping(source = "location.name", target = "locationName")
    HotelResponse toResponse(Hotel hotel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHotel(@MappingTarget Hotel hotel, HotelRequest request);
}
