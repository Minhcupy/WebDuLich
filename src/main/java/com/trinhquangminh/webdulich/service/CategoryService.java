package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.CategoryRequest;
import com.trinhquangminh.webdulich.dto.response.CategoryResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.CategoryMapper;
import com.trinhquangminh.webdulich.model.Category;
import com.trinhquangminh.webdulich.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public Page<CategoryResponse> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toResponse);
    }

    public CategoryResponse getById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toResponse(category);
    }

    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public CategoryResponse update(Integer id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        // kiểm tra nếu tên mới đã tồn tại ở category khác
        if (categoryRepository.existsByNameIgnoreCase(request.getName())
                && !category.getName().equalsIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }

        categoryMapper.updateCategory(category, request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    public void delete(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }

    public Page<CategoryResponse> searchByName(String name, Pageable pageable) {
        return categoryRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(categoryMapper::toResponse);
    }
}
