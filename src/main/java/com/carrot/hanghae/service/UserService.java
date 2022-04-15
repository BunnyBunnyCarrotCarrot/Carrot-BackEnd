package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.UserRequestDto;
import com.carrot.hanghae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void registerUser(UserRequestDto requestDto) {
        String userId = requestDto.getUserId();

        String userPw = passwordEncoder.encode(requestDto.getUserPw());


        User user = new User(userId, userPw);

        userRepository.save(user);

    }
}
