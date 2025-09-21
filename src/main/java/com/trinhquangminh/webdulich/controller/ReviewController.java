package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.ReviewRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.ReviewResponse;
import com.trinhquangminh.webdulich.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ReviewController {

    ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<ReviewResponse>> getAll() {
        return ApiResponse.<List<ReviewResponse>>builder()
                .result(reviewService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ReviewResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<ReviewResponse>builder()
                .result(reviewService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<ReviewResponse> create(@RequestBody ReviewRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .result(reviewService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ReviewResponse> update(@PathVariable Integer id, @RequestBody ReviewRequest request) {
        return ApiResponse.<ReviewResponse>builder()
                .result(reviewService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        reviewService.delete(id);
        return ApiResponse.<String>builder()
                .result("Review deleted successfully")
                .build();
    }

    @GetMapping("/tour/{tourId}")
    public ApiResponse<List<ReviewResponse>> getByTourId(@PathVariable Integer tourId) {
        return ApiResponse.<List<ReviewResponse>>builder()
                .result(reviewService.getByTourId(tourId))
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<ReviewResponse>> getByUserId(@PathVariable Integer userId) {
        return ApiResponse.<List<ReviewResponse>>builder()
                .result(reviewService.getByUserId(userId))
                .build();
    }
}
