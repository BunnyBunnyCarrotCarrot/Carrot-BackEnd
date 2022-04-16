package com.carrot.hanghae.dto;


import com.carrot.hanghae.domain.Category;
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
//    private List<String> imageUrl;
    private Category category;
}
