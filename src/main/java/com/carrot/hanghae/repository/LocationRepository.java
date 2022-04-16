package com.carrot.hanghae.repository;

import com.carrot.hanghae.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
