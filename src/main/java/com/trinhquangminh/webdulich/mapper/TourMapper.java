package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.TourRequest;
import com.trinhquangminh.webdulich.dto.response.TourResponse;
import com.trinhquangminh.webdulich.model.Category;
import com.trinhquangminh.webdulich.model.Location;
import com.trinhquangminh.webdulich.model.Tour;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TourMapper {

    // Entity -> Response
    @Mapping(source = "categoryId.id", target = "categoryId")
    @Mapping(target = "categoryName", expression = "java(tour.getCategoryId() != null ? tour.getCategoryId().getName() : null)")
    @Mapping(source = "locationId.id", target = "locationId")
    @Mapping(target = "locationName", expression = "java(tour.getLocationId() != null ? tour.getLocationId().getName() : null)")
    TourResponse toTourResponse(Tour tour);

    // Request -> Entity (không set category/location ở đây)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", ignore = true) // sẽ set trong Service
    @Mapping(target = "locationId", ignore = true) // sẽ set trong Service
    Tour toTour(TourRequest request);

    // Update Entity từ Request (không set category/location ở đây)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "locationId", ignore = true)
    void updateTour(@MappingTarget Tour entity, TourRequest request);
}
