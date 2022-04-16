package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.Category;
import com.carrot.hanghae.domain.ImageUrl;
import com.carrot.hanghae.domain.Item;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.ItemRequestDto;
import com.carrot.hanghae.dto.ItemResponseDto;
import com.carrot.hanghae.repository.CategoryRepository;
import com.carrot.hanghae.repository.ImageUrlRepository;
import com.carrot.hanghae.repository.ItemRepository;
import com.carrot.hanghae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ImageUrlRepository imageUrlRepository;

    //(Write.html)게시글 작성
    @Transactional
    public ItemResponseDto registerItem(ItemRequestDto ItemDto, List<String> imageUrls) { //User user

        String title = ItemDto.getTitle();
        int price = ItemDto.getPrice();
        String about = ItemDto.getAbout();
        Long categoryId = ItemDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 DB에 존재하지 않습니다.")
        );

        //잘 들어왔는지 콘솔에서 확인!!!!!(마지막에 지우쟈!)
        System.out.println("title : "+ title);
        System.out.println("price : "+ price);
        System.out.println("about : "+ about);
        System.out.println("category : "+ category);

        if (title == null) {
            throw new IllegalArgumentException("내용을 적어주세요.");
        } else if (price < 0) {
            throw new IllegalArgumentException("0원 이상의 가격을 넣어주세요.");
        } else if (about == null) {
            throw new IllegalArgumentException("상세 설명을 넣어주세요.");
        }

        //user 임의 생성(test 끝나면 지우기!!!!)
        User user = userRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없어용!!")
        );

        Item item = new Item(title, price, about, user, category);
        itemRepository.save(item);


        //이미지 URL 저장하기
        List<String> images = new ArrayList<>(); //return 값 보려구요~
        for(String imageUrl : imageUrls){
            ImageUrl image = new ImageUrl(imageUrl, item);
            imageUrlRepository.save(image);
            images.add(image.getImageUrl());
        }

        //return 값 생성
        return new ItemResponseDto(title, price, images, category);
    }

    public ItemResponseDto updateItem(Long itemId, ItemRequestDto itemDto, List<String> imageUrls) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다."));
        System.out.println(item);
        Category category = categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("해당 카테고리가 없습니다."));
        item.update(itemDto, category);
        System.out.println(item);

        //이미지 URL 저장하기
        List<String> images = new ArrayList<>(); //return 값 보려구요~
        for(String imageUrl : imageUrls){
            ImageUrl image = new ImageUrl(imageUrl, item);
            imageUrlRepository.save(image);
            images.add(image.getImageUrl());
        }

        return new ItemResponseDto(item, images);
    }
}
