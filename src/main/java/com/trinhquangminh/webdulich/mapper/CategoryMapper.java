package com.trinhquangminh.webdulich.mapper;

import com.trinhquangminh.webdulich.dto.request.CategoryRequest;
import com.trinhquangminh.webdulich.dto.response.CategoryResponse;
import com.trinhquangminh.webdulich.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}
