package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.TourRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.TourResponse;
import com.trinhquangminh.webdulich.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tours")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TourController {

    TourService tourService;

    // Lấy tất cả tour
    @GetMapping
    public ApiResponse<List<TourResponse>> getAllTours() {
        return ApiResponse.<List<TourResponse>>builder()
                .result(tourService.getAllTours())
                .build();
    }

    // Lấy tour theo ID
    @GetMapping("/{id}")
    public ApiResponse<TourResponse> getTourById(@PathVariable Integer id) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.getTourById(id))
                .build();
    }

    // Tạo tour mới
    @PostMapping
    public ApiResponse<TourResponse> createTour(@RequestBody TourRequest request) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.createTour(request))
                .build();
    }

    // Cập nhật tour
    @PutMapping("/{id}")
    public ApiResponse<TourResponse> updateTour(
            @PathVariable Integer id,
            @RequestBody TourRequest request) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.updateTour(id, request))
                .build();
    }

    // Xóa tour
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTour(@PathVariable Integer id) {
        tourService.deleteTour(id);
        return ApiResponse.<Void>builder().build();
    }

    // Tìm kiếm tour theo tên
    @GetMapping("/search")
    public ApiResponse<Page<TourResponse>> searchTours(
            @RequestParam("name") String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ApiResponse.<Page<TourResponse>>builder()
                .result(tourService.searchToursByName(name, pageable))
                .build();
    }

    // Lọc tour nâng cao
    @GetMapping("/filter")
    public ApiResponse<Map<String, Object>> filterTours(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TourResponse> pageResult = tourService.filterTours(name, minPrice, maxPrice, startDate, status, pageable);

        // Chuyển Page thành Map để JSON ổn định
        Map<String, Object> responseMap = Map.of(
                "content", pageResult.getContent(),
                "page", pageResult.getNumber(),
                "size", pageResult.getSize(),
                "totalElements", pageResult.getTotalElements(),
                "totalPages", pageResult.getTotalPages(),
                "isLast", pageResult.isLast()
        );

        return ApiResponse.<Map<String, Object>>builder()
                .result(responseMap)
                .build();
    }

}
