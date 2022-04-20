package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.Category;
import com.carrot.hanghae.domain.ImageUrl;
import com.carrot.hanghae.domain.Item;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.*;
import com.carrot.hanghae.repository.CategoryRepository;
import com.carrot.hanghae.repository.ImageUrlRepository;
import com.carrot.hanghae.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ImageUrlRepository imageUrlRepository;
    private final S3Service s3Service;

    //카테고리 조회
    public List<CategoryDto> findCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> allCategories = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());
            allCategories.add(categoryDto);
        }
        return allCategories;
    }

    //게시글 작성
    @Transactional
    public ItemResponseDto registerItem(ItemRequestDto itemDto, List<MultipartFile> files, User user) {

        String title = itemDto.getTitle();
        int price = itemDto.getPrice();
        String about = itemDto.getAbout();
        Long categoryId = itemDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
        //유효성검사
                .orElseThrow( () -> new IllegalArgumentException("해당 카테고리가 없습니다!"));
         if (title == null) {
            throw new IllegalArgumentException("내용을 적어주세요.");
        } else if (price < 0) {
            throw new IllegalArgumentException("0원 이상의 가격을 넣어주세요.");
        } else if (about == null) {
            throw new IllegalArgumentException("상세 설명을 넣어주세요.");
        }
        //item 저장
        Item item = new Item(title, price, about, user, category);
        itemRepository.save(item);

        //이미지 URL 저장하기
        List<String> imagePaths = s3Service.upload(files);
        System.out.println("최초 게시하는 Image경로들 모아놓은것 :"+ imagePaths);

        List<String> images = new ArrayList<>(); //return 값 확인
        for(String imageUrl : imagePaths){
            ImageUrl image = new ImageUrl(imageUrl, item);
            imageUrlRepository.save(image);
            item.addImage(image);
        }
        //return 값 생성
        return new ItemResponseDto(title, price, images, category);
    }

    //게시글 수정
    public ItemResponseDto updateItem(Long itemId, ItemRequestDto itemDto, List<MultipartFile> files, User user) {
        //item
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));
        //작성자 검사
        Long itemUserId = item.getUser().getId();
        System.out.println("itemUserId = " + itemUserId);
        if (!user.getId().equals(itemUserId)){
            throw new IllegalArgumentException("작성자가 아니므로, 해당 게시글을 수정할 수 없습니다.");
        }
        //카테고리
        Category category = categoryRepository.findById(itemDto.getCategoryId())
        //유효성 검사
                .orElseThrow(() -> new IllegalStateException("해당 카테고리가 없습니다."));
        if (itemDto.getTitle() == null) {
            throw new IllegalArgumentException("내용을 적어주세요.");
        } else if (itemDto.getPrice() < 0) {
            throw new IllegalArgumentException("0원 이상의 가격을 넣어주세요.");
        } else if (itemDto.getAbout() == null) {
            throw new IllegalArgumentException("상세 설명을 넣어주세요.");
        }

        List<String> imagePaths = s3Service.update(itemId, files);
        System.out.println("수정된 Image경로들 모아놓은것 :"+ imagePaths);

        item.update(itemDto, category);

        //이미지 URL 저장하기
        List<String> images = new ArrayList<>(); //return 값 확인
        for(String imageUrl : imagePaths){
            ImageUrl image = new ImageUrl(imageUrl, item);
            imageUrlRepository.save(image);
            images.add(image.getImageUrls());
        }
        return new ItemResponseDto(item, images);
    }

    //게시글 상세페이지 조회
    public UserItemResponseDto getUserItem(Long itemId, User user) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 게시글이 없습니다."));
        List<ImageUrl> imageUrls = imageUrlRepository.findByItemId(itemId);
        List<String> images = new ArrayList<>();
        for (ImageUrl imageUrl: imageUrls){
            images.add(imageUrl.getImageUrls());
        }
        Category category = item.getCategory();

        //좋아요 완성되면 넣기
        Random rand = new Random();
        int likeCount = rand.nextInt(100);
        boolean likeState = true;

        return new UserItemResponseDto(item, user, images, likeCount, likeState, category);
    }

    //item 삭제
    public Long deleteItem(Long itemId, User user) {
        //item 유효성 검사
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));
        //작성자 검사
        Long itemUserId = item.getUser().getId();
        if (!user.getId().equals(itemUserId)){
            throw new IllegalArgumentException("작성자가 아니므로, 해당 게시글을 삭제할 수 없습니다.");
        }
        //S3 사진, ImageURl, item 삭제
        s3Service.delete(itemId);
        imageUrlRepository.deleteAllByItemId(itemId);
        itemRepository.deleteById(itemId);
        System.out.println("삭제가 되었어요~~");

        return itemId;
    }
}
