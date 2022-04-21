package com.carrot.hanghae.dto;

import com.carrot.hanghae.domain.ImageUrl;
import com.carrot.hanghae.domain.Item;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainResponseDto {
    private Long itemId;
    private String userName;
    private String title;
    private int price;
    private List<String> imageUrls;
    private Boolean soldOut;
    private Long likeCount;
    private Boolean likeState;
    private LocalDateTime modifiedAt;

    public MainResponseDto(Item item){
        this.itemId = item.getId();
        this.userName = item.getUser().getUserName();
        this.title = item.getTitle();
        this.price = item.getPrice();
        this.imageUrls = new ArrayList<>();
        this.soldOut = item.getStatus();
        this.likeCount = 0L ;
        this.likeState = false;
        this.modifiedAt= item.getModifiedAt();

        List<ImageUrl> imageUrlList = item.getImageUrls();
        for(ImageUrl imageUrl : imageUrlList){
            imageUrls.add(imageUrl.getImageUrls());
        }
    }
}
