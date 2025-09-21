package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.BookingRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.BookingResponse;
import com.trinhquangminh.webdulich.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;

    @GetMapping
    public ApiResponse<List<BookingResponse>> getAll() {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<BookingResponse> create(@RequestBody BookingRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BookingResponse> update(@PathVariable Integer id,
                                               @RequestBody BookingRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Integer id) {
        bookingService.delete(id);
        return ApiResponse.<String>builder()
                .result("Booking deleted successfully")
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<BookingResponse>> getByUserId(@PathVariable Integer userId) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getByUserId(userId))
                .build();
    }

    @GetMapping("/tour/{tourId}")
    public ApiResponse<List<BookingResponse>> getByTourId(@PathVariable Integer tourId) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getByTourId(tourId))
                .build();
    }

    @GetMapping("/status")
    public ApiResponse<List<BookingResponse>> getByStatus(@RequestParam String status) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getByStatus(status))
                .build();
    }
}
