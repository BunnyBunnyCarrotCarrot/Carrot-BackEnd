package com.carrot.hanghae.repository;

import com.carrot.hanghae.domain.Good;
import com.carrot.hanghae.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {
    Good findByItemAndUserId(Item item, Long userId);
    @Transactional
    void deleteById(Long id);

    List<Good> findAllByItemId(Long itemId);
}
