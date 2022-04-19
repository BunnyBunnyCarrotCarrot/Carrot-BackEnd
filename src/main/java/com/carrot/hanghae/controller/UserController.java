package com.carrot.hanghae.controller;

import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.dto.UserResponseDto;
import com.carrot.hanghae.dto.UserSignupRequestDto;
import com.carrot.hanghae.repository.LocationRepository;
import com.carrot.hanghae.security.UserDetailsImpl;
import com.carrot.hanghae.service.S3Service;
import com.carrot.hanghae.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final S3Service s3Service;
    private final LocationRepository locationRepository;

    @GetMapping("/user/signup")
    public List<Location> registerUser() {
        return locationRepository.findAll();

    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserSignupRequestDto requestDto) {
        userService.registerUser(requestDto);
//        log.info("회원가입 완료");
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }

    @GetMapping("/user/detail")
    public UserResponseDto getUserDetail(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        User user = userDetails.getUser();
//        UserResponseDto userResponseDto = new UserResponseDto(user);
//        System.out.println("userResponseDto = " + userResponseDto);
        return new UserResponseDto(userDetails.getUser());
    }

    @PostMapping("/user/detail")
    public void updateUserDetail(@RequestParam  Long locationId, @RequestParam MultipartFile file, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String imagePath = s3Service.uploadOne(file);
        userService.updateUser(locationId, imagePath, userDetails.getUser().getId());
    }


}
