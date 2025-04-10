package com.offnine.billingSoftware.service;

import com.offnine.billingSoftware.io.CategoryRequest;
import com.offnine.billingSoftware.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest request);

}
