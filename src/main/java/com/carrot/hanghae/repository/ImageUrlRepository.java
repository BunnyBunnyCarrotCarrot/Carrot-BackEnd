package com.carrot.hanghae.repository;


import com.carrot.hanghae.domain.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageUrlRepository extends JpaRepository<ImageUrl, Long> {
    List<ImageUrl> findByItemId(Long itemId);
}