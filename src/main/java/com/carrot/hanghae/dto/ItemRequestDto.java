package com.carrot.hanghae.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItemRequestDto {
    private String title;
    private int price;
    private String about;
    private Long categoryId;
}
