package com.carrot.hanghae.repository;


import com.carrot.hanghae.domain.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageUrlRepository extends JpaRepository<ImageUrl, Long> {
}