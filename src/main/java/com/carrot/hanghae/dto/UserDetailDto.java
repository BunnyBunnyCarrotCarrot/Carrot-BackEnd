package com.carrot.hanghae.dto;

import com.carrot.hanghae.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailDto {
    private final String userId;
    private final String userName;
    private final Location location;
    private final String userImageUrl;
}
