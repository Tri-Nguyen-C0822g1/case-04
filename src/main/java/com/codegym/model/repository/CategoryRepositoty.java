package com.codegym.model.repository;

import com.codegym.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoty extends JpaRepository<Category, Long> {
}
