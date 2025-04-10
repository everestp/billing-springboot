package com.offnine.billingSoftware.service.impl;

import com.offnine.billingSoftware.entity.CategoryEntity;
import com.offnine.billingSoftware.io.CategoryRequest;
import com.offnine.billingSoftware.io.CategoryResponse;
import com.offnine.billingSoftware.repository.CategoryRepository;
import com.offnine.billingSoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponse add(CategoryRequest request) {
       CategoryEntity newCategory =  convertToEntity(request);
       newCategory = categoryRepository.save(newCategory);

     return convertToResponse(newCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        return  CategoryResponse.builder().name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .categoryId(newCategory.getCategoryId())
                .imgUrl(newCategory.getImgUrl())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())


                .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        return CategoryEntity.builder().categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
