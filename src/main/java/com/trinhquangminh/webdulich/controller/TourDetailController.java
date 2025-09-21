package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.TourDetailRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.TourDetailResponse;
import com.trinhquangminh.webdulich.service.TourDetailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/tour-details")
public class TourDetailController {

    TourDetailService tourDetailService;

    @GetMapping
    public ApiResponse<List<TourDetailResponse>> getAll() {
        return ApiResponse.<List<TourDetailResponse>>builder()
                .result(tourDetailService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TourDetailResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourDetailService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<TourDetailResponse> create(@RequestBody TourDetailRequest request) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourDetailService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TourDetailResponse> update(
            @PathVariable Integer id,
            @RequestBody TourDetailRequest request) {
        return ApiResponse.<TourDetailResponse>builder()
                .result(tourDetailService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        tourDetailService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/tour/{tourId}")
    public ApiResponse<List<TourDetailResponse>> getByTour(@PathVariable Integer tourId) {
        return ApiResponse.<List<TourDetailResponse>>builder()
                .result(tourDetailService.getByTourId(tourId))
                .build();
    }

    @GetMapping("/location/{locationId}")
    public ApiResponse<List<TourDetailResponse>> getByLocation(@PathVariable Integer locationId) {
        return ApiResponse.<List<TourDetailResponse>>builder()
                .result(tourDetailService.getByLocationId(locationId))
                .build();
    }
}
