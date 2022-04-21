package com.carrot.hanghae.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodRequestDto {
    private Long itemId;

    public GoodRequestDto(Long itemId){
        this.itemId = itemId;
    }
}
