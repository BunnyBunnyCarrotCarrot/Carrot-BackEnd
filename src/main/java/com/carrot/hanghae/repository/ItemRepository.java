package com.carrot.hanghae.repository;


import com.carrot.hanghae.domain.Item;
import com.carrot.hanghae.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByOrderByModifiedAtDesc();

    @Query ("select i from Item i " +
            "join User u on i.user.id = u.id " +
            "where u.location = :location")
    List<Item> findAllInnerFetchJoin(Location location);
}