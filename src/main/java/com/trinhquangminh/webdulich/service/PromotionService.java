package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.PromotionRequest;
import com.trinhquangminh.webdulich.dto.response.PromotionResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.PromotionMapper;
import com.trinhquangminh.webdulich.model.Promotion;
import com.trinhquangminh.webdulich.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PromotionService {

    PromotionRepository promotionRepository;
    PromotionMapper promotionMapper;

    public Page<PromotionResponse> getAll(Pageable pageable) {
        return promotionRepository.findAll(pageable).map(promotionMapper::toResponse);
    }

    public PromotionResponse getById(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
        return promotionMapper.toResponse(promotion);
    }

    public PromotionResponse create(PromotionRequest request) {
        Promotion promotion = promotionMapper.toEntity(request);
        return promotionMapper.toResponse(promotionRepository.save(promotion));
    }

    public PromotionResponse update(Integer id, PromotionRequest request) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
        promotionMapper.updatePromotion(promotion, request);
        return promotionMapper.toResponse(promotionRepository.save(promotion));
    }

    public void delete(Integer id) {
        if (!promotionRepository.existsById(id)) {
            throw new AppException(ErrorCode.PROMOTION_NOT_FOUND);
        }
        promotionRepository.deleteById(id);
    }

    public Page<PromotionResponse> searchByCode(String code, Pageable pageable) {
        return promotionRepository.findByPromotionCodeContainingIgnoreCase(code, pageable)
                .map(promotionMapper::toResponse);
    }
}
