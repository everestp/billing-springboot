package com.offnine.billingSoftware.service.impl;

import com.offnine.billingSoftware.entity.CategoryEntity;
import com.offnine.billingSoftware.io.CategoryRequest;
import com.offnine.billingSoftware.io.CategoryResponse;
import com.offnine.billingSoftware.repository.CategoryRepository;
import com.offnine.billingSoftware.service.CategoryService;
import com.offnine.billingSoftware.service.FIleUploadService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {
 private final FIleUploadService fIleUploadService;
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponse add(CategoryRequest request , MultipartFile file) {
        String fileUrl = fIleUploadService.uploadFile(file);
       CategoryEntity newCategory =  convertToEntity(request);
       newCategory.setImgUrl(fileUrl);
       newCategory = categoryRepository.save(newCategory);

     return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> read() {
     return   categoryRepository.findAll().stream().map(this::convertToResponse).toList();

    }

    @Override
    public void delete(String categoryId) {
       CategoryEntity existingCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(()->new RuntimeException("Category not found"+categoryId));
       fIleUploadService.deleteFile(existingCategory.getImgUrl());
       categoryRepository.delete(existingCategory);
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
