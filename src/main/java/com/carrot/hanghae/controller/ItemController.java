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
    private final S3Service s3Service;
    private final ImageUrlRepository imageUrlRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    //게시글 작성 시 카테고리 조회
    @GetMapping("/api/item")
    public List<CategoryDto> findCategorys(){
        return itemService.findCategorys();
    }

    //게시글 작성
    @PostMapping("/api/item")
    public ItemResponseDto createItem(
            @RequestPart ItemRequestDto itemDto,
            @RequestPart List<MultipartFile> files,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<String> imagePaths = s3Service.upload(files);
        System.out.println("Image경로들 모아놓은것 :"+ imagePaths);
        return itemService.registerItem(itemDto, imagePaths, userDetails.getUser());
    }

    //게시글 수정
    @PutMapping("/api/item/{itemId}/update")
    public ItemResponseDto updateItem(
            @PathVariable Long itemId,
            @RequestPart ItemRequestDto itemDto,
            @RequestPart List<MultipartFile> files,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<String> imagePaths = s3Service.update(files, userDetails.getUser(), itemId);
        System.out.println("Image경로들 모아놓은것 :"+ imagePaths);
        return itemService.updateItem(itemId, itemDto, imagePaths);
    }

    //게시글 삭제
    @DeleteMapping("/api/item/{itemId}")
    public Long deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        s3Service.delete(userDetails.getUser(), itemId);
        imageUrlRepository.deleteAllByItemId(itemId);
        itemRepository.deleteById(itemId);
        System.out.println("삭제가 되었어요~~");
        return itemId;
    }

    //게시글 상세페이지 조회
    @GetMapping("/api/item/{itemId}/details")
    public UserItemResponseDto getUserItem(@PathVariable Long itemId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return itemService.getUserItem(itemId, userDetails.getUser());
    }
}

