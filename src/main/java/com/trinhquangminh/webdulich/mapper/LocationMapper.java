package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.LocationRequest;
import com.trinhquangminh.webdulich.dto.response.LocationResponse;
import com.trinhquangminh.webdulich.model.Location;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationResponse toLocationResponse(Location location);

    @Mapping(target = "id", ignore = true)
    Location toLocation(LocationRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLocation(@MappingTarget Location location, LocationRequest request);
}
