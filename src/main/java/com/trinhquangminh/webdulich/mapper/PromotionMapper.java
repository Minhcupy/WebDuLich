package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.PromotionRequest;
import com.trinhquangminh.webdulich.dto.response.PromotionResponse;
import com.trinhquangminh.webdulich.model.Promotion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    Promotion toEntity(PromotionRequest request);

    PromotionResponse toResponse(Promotion promotion);

    void updatePromotion(@MappingTarget Promotion promotion, PromotionRequest request);
}
