package com.offnine.billingSoftware.service;

import com.offnine.billingSoftware.io.CategoryRequest;
import com.offnine.billingSoftware.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request, MultipartFile file);
    List<CategoryResponse> read();
    void delete(String categoryId);

}
