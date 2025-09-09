package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.LocationRequest;
import com.trinhquangminh.webdulich.dto.response.LocationResponse;
import com.trinhquangminh.webdulich.service.LocationService;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ApiResponse<Page<LocationResponse>> getAll(Pageable pageable) {
        return ApiResponse.<Page<LocationResponse>>builder()
                .result(locationService.getAll(pageable))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<LocationResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<LocationResponse>builder()
                .result(locationService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<LocationResponse> create(@RequestBody LocationRequest request) {
        return ApiResponse.<LocationResponse>builder()
                .result(locationService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<LocationResponse> update(@PathVariable Integer id, @RequestBody LocationRequest request) {
        return ApiResponse.<LocationResponse>builder()
                .result(locationService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        locationService.delete(id);
    }

    @GetMapping("/search")
    public ApiResponse<Page<LocationResponse>> search(@RequestParam String name, Pageable pageable) {
        return ApiResponse.<Page<LocationResponse>>builder()
                .result(locationService.searchByName(name, pageable))
                .build();
    }
}
