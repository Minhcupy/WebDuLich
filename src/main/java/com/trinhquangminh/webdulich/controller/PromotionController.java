package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.PromotionRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.PromotionResponse;
import com.trinhquangminh.webdulich.service.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PromotionController {

    PromotionService promotionService;

    @GetMapping
    public ApiResponse<Page<PromotionResponse>> getAll(Pageable pageable) {
        return ApiResponse.<Page<PromotionResponse>>builder()
                .result(promotionService.getAll(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PromotionResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<PromotionResponse> create(@RequestBody PromotionRequest request) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PromotionResponse> update(@PathVariable Integer id, @RequestBody PromotionRequest request) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        promotionService.delete(id);
        return ApiResponse.<String>builder()
                .result("Promotion deleted successfully")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<Page<PromotionResponse>> searchByCode(@RequestParam String code, Pageable pageable) {
        return ApiResponse.<Page<PromotionResponse>>builder()
                .result(promotionService.searchByCode(code, pageable))
                .build();
    }
}
