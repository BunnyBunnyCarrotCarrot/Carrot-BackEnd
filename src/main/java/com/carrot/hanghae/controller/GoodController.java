package com.carrot.hanghae.controller;

import com.carrot.hanghae.dto.GoodRequestDto;
import com.carrot.hanghae.security.UserDetailsImpl;
import com.carrot.hanghae.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class GoodController {

    private final GoodService goodService;

    @PostMapping("/api/item/like")
    public boolean getGoods(@RequestBody GoodRequestDto goodRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        boolean good = goodService.getGoods(goodRequestDto, userDetails.getUser());
        return good;
    }
}
