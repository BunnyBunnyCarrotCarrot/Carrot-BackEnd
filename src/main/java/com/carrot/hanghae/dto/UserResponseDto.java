package com.carrot.hanghae.dto;

import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private final String userId;
    private final String userName;
    private final Location location;
    private final String imgUrl;

    public UserResponseDto(User user) {
        this.userName = user.getUserName();
        this.userId = user.getUserId();
        this.location = user.getLocation();
        this.imgUrl = user.getImgUrl();
    }
}
