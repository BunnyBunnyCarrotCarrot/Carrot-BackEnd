package com.carrot.hanghae.repository;

import com.carrot.hanghae.domain.Category;
import com.carrot.hanghae.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
