package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.UserRequestDto;
import com.carrot.hanghae.exception.CustomException;
import com.carrot.hanghae.exception.ErrorCode;
import com.carrot.hanghae.repository.LocationRepository;
import com.carrot.hanghae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.carrot.hanghae.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public void registerUser(UserRequestDto requestDto) throws IllegalArgumentException {
        String userId = requestDto.getUserId();
        String userName = requestDto.getUserName();
        String inputPw = requestDto.getUserPw();
        String inputPw2 = requestDto.getUserPwCheck();
        Long locationId = requestDto.getLocationId();

        //아이디 닉네임 중복 검사
        Optional<User> foundById = userRepository.findByUserId(userId);
        if (foundById.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_USER_ID);
        }
        Optional<User> foundByName = userRepository.findByUserName(userName);
        if (foundByName.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_USER_NAME);
        }
        //지역 코드 검사
        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_LOCATION_ID)
        );
        // 아이디 유효성 검사
        Pattern userIdPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z0-9]{4,15}$");
        Matcher userIdMatcher = userIdPattern.matcher(userId);
        if(userId.length() == 0){
            throw new CustomException(ErrorCode.BLANK_USER_ID);
        }
        if(!userIdMatcher.matches()) {
            throw new CustomException(ErrorCode.INVALID_PATTERN_USER_ID);
        }
        // 닉네임
        Pattern userNamePattern = Pattern.compile("^\\S{2,6}$");
        Matcher userNameMatcher = userNamePattern.matcher(userName);
        if(userName.length() == 0){
            throw new CustomException(ErrorCode.BLANK_USER_NAME);
        }
        if(!userNameMatcher.matches()) {
            throw new CustomException(ErrorCode.INVALID_PATTERN_USER_NAME);
        }

        // 비밀번호 유효성 검사
        Pattern userPwPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$])[A-Za-z\\d!@#$]{8,16}$");
        Matcher userPwMatcher = userPwPattern.matcher(inputPw);
        if(inputPw.length() == 0){
            throw new CustomException(ErrorCode.BLANK_USER_PW);
        }
        if(!userPwMatcher.matches()) {
            throw new CustomException(ErrorCode.INVALID_PATTERN_USER_PW);
        }

        // password 일치여부
        if(inputPw2.length() == 0){
            throw new CustomException(ErrorCode.BLANK_USER_PW_CHECK);
        }
        if(!inputPw.equals(inputPw2)){
            throw new CustomException(ErrorCode.NOT_EQUAL_USER_PW_CHECK);
        }

        String userPw = passwordEncoder.encode(requestDto.getUserPw());
        String userPwCheck = passwordEncoder.encode(requestDto.getUserPwCheck());

        User user = new User(userId, userPw, userName, location);

        userRepository.save(user);
    }
}
