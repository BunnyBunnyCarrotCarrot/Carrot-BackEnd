package com.carrot.hanghae.dto;

import com.carrot.hanghae.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MainUserDto {
    private final String userName;
    private final String location;

    public MainUserDto(User user){
        this.userName = user.getUserName();
        this.location = user.getLocation().getName();
    }
}
