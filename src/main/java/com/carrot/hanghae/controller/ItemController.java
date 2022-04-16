package com.carrot.hanghae.controller;

import com.carrot.hanghae.dto.ItemRequestDto;
import com.carrot.hanghae.dto.ItemResponseDto;
import com.carrot.hanghae.service.ItemService;
import com.carrot.hanghae.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;
    private final S3Service s3Service;

    @PostMapping("/api/item")
    public ItemResponseDto createPost(
            @RequestPart ItemRequestDto postDtos
            //@RequestPart List<MultipartFile> file  // , @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        //List<String> imagePath = s3Service.upload(file);
//        postDtos.setImageUrl(imagePath);
        //System.out.println("Image경로들 모아놓은것 :" +imagePath);
        return itemService.registerPost(postDtos); //, imagePath  //userDetails.getUser()
    }
}
