package com.offnine.billingSoftware.repository;

import com.offnine.billingSoftware.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Long> {
}
