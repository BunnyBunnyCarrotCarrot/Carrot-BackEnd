package com.carrot.hanghae.controller;

import com.carrot.hanghae.dto.UserRequestDto;
import com.carrot.hanghae.exception.CustomException;
import com.carrot.hanghae.exception.ErrorCode;
import com.carrot.hanghae.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto requestDto) {
        userService.registerUser(requestDto);
//        log.info("회원가입 완료");
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }


}
