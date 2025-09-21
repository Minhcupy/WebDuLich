package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.TourScheduleRequest;
import com.trinhquangminh.webdulich.dto.response.TourScheduleResponse;
import com.trinhquangminh.webdulich.model.Tour;
import com.trinhquangminh.webdulich.model.TourSchedule;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TourScheduleMapper {

    @Mapping(source = "tourId.id", target = "tourId")
    TourScheduleResponse toTourScheduleResponse(TourSchedule tourSchedule);

    @Mapping(target = "tourId", ignore = true)
    TourSchedule toTourSchedule(TourScheduleRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "tourId", ignore = true)
    void updateTourSchedule(@MappingTarget TourSchedule tourSchedule, TourScheduleRequest request, @Context Tour tour);
}
