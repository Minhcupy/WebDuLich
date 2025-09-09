package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.ReviewRequest;
import com.trinhquangminh.webdulich.dto.response.ReviewResponse;
import com.trinhquangminh.webdulich.model.Review;
import com.trinhquangminh.webdulich.model.Tour;
import com.trinhquangminh.webdulich.model.Users;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "userId", target = "userId.id")
    @Mapping(source = "tourId", target = "tourId.id")
    Review toEntity(ReviewRequest request);

    @Mapping(source = "userId.id", target = "userId")
    @Mapping(source = "tourId.id", target = "tourId")
    ReviewResponse toResponse(Review review);

    @Mapping(source = "userId", target = "userId.id")
    @Mapping(source = "tourId", target = "tourId.id")
    void updateReview(@MappingTarget Review review, ReviewRequest request);
}
