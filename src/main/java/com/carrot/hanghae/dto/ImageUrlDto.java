package com.carrot.hanghae.dto;

import com.carrot.hanghae.domain.ImageUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageUrlDto {
    private Long id;
    private String imageUrls;


    public ImageUrlDto(ImageUrl imageUrl){
        this.id = imageUrl.getId();
        this.imageUrls = imageUrl.getImageUrls();
    }
}
