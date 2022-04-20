package com.carrot.hanghae.controller;

import com.carrot.hanghae.domain.Item;
import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.repository.ItemRepository;
import com.carrot.hanghae.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ItemRepository itemRepository;

    @GetMapping("/api/main")
    public List<Item> getAllItems(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        Location location = user.getLocation();

        return itemRepository.findAllInnerFetchJoin(location);
    }
}
