package com.carrot.hanghae.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSignupRequestDto {
    private final String userId;
    private final String userName;
    private final String userPw;
    private final String userPwCheck;
    private final Long userLocation;
}
