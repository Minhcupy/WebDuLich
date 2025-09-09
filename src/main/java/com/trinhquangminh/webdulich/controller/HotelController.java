package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.HotelRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.HotelResponse;
import com.trinhquangminh.webdulich.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {

    HotelService hotelService;

    @GetMapping
    public ApiResponse<Page<HotelResponse>> getAll(Pageable pageable) {
        return ApiResponse.<Page<HotelResponse>>builder()
                .result(hotelService.getAll(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<HotelResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<HotelResponse>builder()
                .result(hotelService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<HotelResponse> create(@RequestBody HotelRequest request) {
        return ApiResponse.<HotelResponse>builder()
                .result(hotelService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<HotelResponse> update(@PathVariable Integer id, @RequestBody HotelRequest request) {
        return ApiResponse.<HotelResponse>builder()
                .result(hotelService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return ApiResponse.<String>builder()
                .result("Hotel deleted successfully")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<Page<HotelResponse>> searchByName(@RequestParam String name, Pageable pageable) {
        return ApiResponse.<Page<HotelResponse>>builder()
                .result(hotelService.searchByName(name, pageable))
                .build();
    }
}
