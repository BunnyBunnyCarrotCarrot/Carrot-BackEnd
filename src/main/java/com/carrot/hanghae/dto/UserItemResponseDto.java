package com.carrot.hanghae.dto;

import com.carrot.hanghae.domain.Category;
import com.carrot.hanghae.domain.Item;
import com.carrot.hanghae.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserItemResponseDto {
    private Long itemId;
    private String userName;
    private String title;
    private int price;
    private String about;
    private List<String> imageUrls;
    private Long categoryId;
    private String categoryName;
    private int likeCount;
    private boolean likeState;
    private LocalDateTime modefiedAt;


    public UserItemResponseDto(Item item, User user, List<String> imageUrls, int likeCount, boolean likeState, Category category){
        this.itemId = item.getId();
        this.userName = user.getUserName();
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.about = item.getAbout();
        this.imageUrls = imageUrls;
        this.likeCount = likeCount;
        this.likeState = likeState;
        this.categoryId = category.getId();
        this.categoryName = category.getName();
        this.modefiedAt = item.getModifiedAt();
    }
}

