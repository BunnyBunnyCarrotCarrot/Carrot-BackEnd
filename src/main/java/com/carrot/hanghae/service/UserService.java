package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.UserRequestDto;
import com.carrot.hanghae.repository.LocationRepository;
import com.carrot.hanghae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        Optional<User> foundByName = userRepository.findByUserName(userName);
        if (foundByName.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 닉네임 이 존재합니다.");
        }
        //지역 코드 검사
        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new IllegalArgumentException("잘못된 접근입니다.")
        );
        // 아이디 유효성 검사
        Pattern userIdPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[a-zA-Z0-9]{4,15}$");
        Matcher userIdMatcher = userIdPattern.matcher(userId);
        if(userId.length() == 0){
            throw new IllegalArgumentException("아이디는 필수 입력값입니다.");
        }
        if(!userIdMatcher.matches()) {
            throw new IllegalArgumentException("4자~15자 영어 숫자 조합으로 입력해주세요.");
        }
        // 닉네임
        Pattern userNamePattern = Pattern.compile("^\\S{2,6}$");
        Matcher userNameMatcher = userNamePattern.matcher(userName);
        if(userName.length() == 0){
            throw new IllegalArgumentException("닉네임은 필수 입력값입니다.");
        }
        if(!userNameMatcher.matches()) {
            throw new IllegalArgumentException("2자~6자로 입력해주세요.");
        }

        // 비밀번호 유효성 검사
        Pattern userPwPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$])[A-Za-z\\d!@#$]{8,16}$");
        Matcher userPwMatcher = userPwPattern.matcher(inputPw);
        if(inputPw.length() == 0){
            throw new IllegalArgumentException("비밀번호는 필수 입력값입니다.");
        }
        if(!userPwMatcher.matches()) {
            throw new IllegalArgumentException("비밀번호는 영어,숫자,특수문자!@#$ 를 사용하여 6~15자로 입력해주세요.");
        }

        // password 일치여부
        if(inputPw2.length() == 0){
            throw new IllegalArgumentException("비밀번호 재확인은 필수입니다.");
        }
        if(!inputPw.equals(inputPw2)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String userPw = passwordEncoder.encode(requestDto.getUserPw());
        String userPwCheck = passwordEncoder.encode(requestDto.getUserPwCheck());

        User user = new User(userId, userPw, userName, location);

        userRepository.save(user);

    }
}
