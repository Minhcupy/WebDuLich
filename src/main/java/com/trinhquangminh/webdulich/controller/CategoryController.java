package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.CategoryRequest;
import com.trinhquangminh.webdulich.dto.response.ApiResponse;
import com.trinhquangminh.webdulich.dto.response.CategoryResponse;
import com.trinhquangminh.webdulich.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    // Lấy tất cả categories (phân trang)
    @GetMapping
    public ApiResponse<Page<CategoryResponse>> getAll(Pageable pageable) {
        return ApiResponse.<Page<CategoryResponse>>builder()
                .result(categoryService.getAll(pageable))
                .build();
    }

    // Lấy category theo ID
    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getById(id))
                .build();
    }

    // Tạo category mới
    @PostMapping
    public ApiResponse<CategoryResponse> create(@RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.create(request))
                .build();
    }

    // Cập nhật category
    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> update(@PathVariable Integer id, @RequestBody CategoryRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.update(id, request))
                .build();
    }

    // Xóa category
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    // Tìm kiếm category theo tên
    @GetMapping("/search")
    public ApiResponse<Page<CategoryResponse>> searchByName(@RequestParam String name, Pageable pageable) {
        return ApiResponse.<Page<CategoryResponse>>builder()
                .result(categoryService.searchByName(name, pageable))
                .build();
    }
}
