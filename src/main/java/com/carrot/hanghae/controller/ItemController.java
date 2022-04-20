package com.carrot.hanghae.controller;

import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.CategoryDto;
import com.carrot.hanghae.dto.ItemRequestDto;
import com.carrot.hanghae.dto.ItemResponseDto;
import com.carrot.hanghae.dto.UserItemResponseDto;
import com.carrot.hanghae.repository.ImageUrlRepository;
import com.carrot.hanghae.repository.ItemRepository;
import com.carrot.hanghae.repository.UserRepository;
import com.carrot.hanghae.security.UserDetailsImpl;
import com.carrot.hanghae.service.ItemService;
import com.carrot.hanghae.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    //게시글 작성 시 카테고리 조회
    @GetMapping("/api/item")
    public List<CategoryDto> findCategories(){
        return itemService.findCategories();
    }

    //게시글 작성
    @RequestMapping(value = "/api/item", method = {RequestMethod.POST}, headers = ("content-type=multipart/*"))
    public ItemResponseDto createItem(
            @RequestPart(value = "itemDto") ItemRequestDto itemDto,
            @RequestPart(value = "files") List<MultipartFile> files,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return itemService.registerItem(itemDto, files, userDetails.getUser());
    }

    //게시글 수정
    @PutMapping("/api/item/{itemId}/update")
    public ItemResponseDto updateItem(
            @PathVariable Long itemId,
            @RequestPart ItemRequestDto itemDto,
            @RequestPart List<MultipartFile> files,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return itemService.updateItem(itemId, itemDto, files, userDetails.getUser());
    }

    //게시글 삭제
    @DeleteMapping("/api/item/{itemId}")
    public Long deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return itemService.deleteItem(itemId, userDetails.getUser());
    }

    //게시글 상세페이지 조회
    @GetMapping("/api/item/{itemId}/details")
    public UserItemResponseDto getUserItem(@PathVariable Long itemId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return itemService.getUserItem(itemId, userDetails.getUser());
    }
}

