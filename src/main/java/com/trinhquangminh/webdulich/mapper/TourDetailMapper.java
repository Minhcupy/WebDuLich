package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.TourDetailRequest;
import com.trinhquangminh.webdulich.dto.response.TourDetailResponse;
import com.trinhquangminh.webdulich.model.TourDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourDetailMapper {

    @Mapping(source = "tourId.name", target = "tourName")
    @Mapping(source = "locationId.name", target = "locationName")
    TourDetailResponse toTourDetailResponse(TourDetail tourDetail);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tourId", expression = "java(new Tour(request.getTourId()))")
    @Mapping(target = "locationId", expression = "java(new Location(request.getLocationId()))")
    TourDetail toTourDetail(TourDetailRequest request);

    @Mapping(target = "tourId", expression = "java(new Tour(request.getTourId()))")
    @Mapping(target = "locationId", expression = "java(new Location(request.getLocationId()))")
    void updateTourDetail(@MappingTarget TourDetail entity, TourDetailRequest request);

}
