package com.carrot.hanghae.controller;

import com.carrot.hanghae.domain.ImageUrl;
import com.carrot.hanghae.dto.ItemRequestDto;
import com.carrot.hanghae.dto.ItemResponseDto;
import com.carrot.hanghae.repository.ImageUrlRepository;
import com.carrot.hanghae.repository.ItemRepository;
import com.carrot.hanghae.service.ItemService;
import com.carrot.hanghae.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;
    private final S3Service s3Service;
    private final ImageUrlRepository imageUrlRepository;

    //게시글 작성
    @PostMapping("/api/item")
    public ItemResponseDto createItem(
            @RequestPart ItemRequestDto itemDto,
            @RequestPart List<MultipartFile> files                // , @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<String> imagePaths = s3Service.upload(files);
        System.out.println("Image경로들 모아놓은것 :"+ imagePaths);
        return itemService.registerItem(itemDto, imagePaths);   //userDetails.getUser()
    }

    //게시글 수정
    @PutMapping("/api/item/{itemId}/update")
    public ItemResponseDto updateItem(
            @PathVariable Long itemId,
            @RequestPart ItemRequestDto itemDto,
            @RequestPart List<MultipartFile> files
    ) {
        List<ImageUrl> lastImages = imageUrlRepository.findByItemId(itemId);
        List<String> imagePaths = s3Service.update(files, lastImages);
        System.out.println("Image경로들 모아놓은것 :"+ imagePaths);
        System.out.println("여기나와?"+ itemDto);
        return itemService.updateItem(itemId, itemDto, imagePaths);
    }
}


