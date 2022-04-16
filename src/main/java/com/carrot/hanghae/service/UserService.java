package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.UserRequestDto;
import com.carrot.hanghae.repository.LocationRepository;
import com.carrot.hanghae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public void registerUser(UserRequestDto requestDto) {
        String userId = requestDto.getUserId();

        String userPw = passwordEncoder.encode(requestDto.getUserPw());

        Long locationId = requestDto.getLocationId();

        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );

        User user = new User(userId, userPw, location);

        userRepository.save(user);

    }
}
