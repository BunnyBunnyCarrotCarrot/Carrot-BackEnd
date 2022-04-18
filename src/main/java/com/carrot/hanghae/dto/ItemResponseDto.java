package com.carrot.hanghae.dto;


import com.carrot.hanghae.domain.Category;
import com.carrot.hanghae.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {
    private String title;
    private int price;
    private List<String> imageUrls;
    private Category category;


    public ItemResponseDto(Item item, List<String> imageUrls){
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.imageUrls = imageUrls;
        this.category = item.getCategory();
    }
}
