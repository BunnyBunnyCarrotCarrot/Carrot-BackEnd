package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.UserRequestDto;
import com.carrot.hanghae.exception.CustomException;
import com.carrot.hanghae.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Test
    void 유저등록() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "testUser1",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        userService.registerUser(userRequestDto);

        //then
        User user = userRepository.findByUserId("testUser1").orElseThrow(
                ()-> new IllegalArgumentException("없다")
        );
        assertThat(user.getUserName()).isEqualTo("이름");
        assertThat(user.getLocation().getName()).isEqualTo("서울");
    }

    @Test
    void 유저아이디_숫자없음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "testUser",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("아이디를 4자~15자 영어 숫자 조합으로 입력해주세요.");
    }

    @Test
    void 유저아이디_영어없음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "123456789",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("아이디를 4자~15자 영어 숫자 조합으로 입력해주세요.");
    }

    @Test
    void 유저아이디_범위넘음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "abcdefghijklmnop123456789",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("아이디를 4자~15자 영어 숫자 조합으로 입력해주세요.");
    }

    @Test
    void 유저아이디_범위작음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "ab1",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("아이디를 4자~15자 영어 숫자 조합으로 입력해주세요.");
    }

    @Test
    void 유저아이디_빈값() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("아이디는 필수 입력값입니다.");
    }

    @Test
    void 유저아이디_중복() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );
        userService.registerUser(userRequestDto);
        UserRequestDto userRequestDto2 = new UserRequestDto(
                "test1234",
                "이름123",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("유저아이디가 이미 존재합니다.");
    }

    @Test
    void 유저닉네임_범위넘음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "범위넓게범위넓게범위넓게",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("닉네임을 2자~6자로 입력해주세요.");
    }

    @Test
    void 유저닉네임_범위작음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "범",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("닉네임을 2자~6자로 입력해주세요.");
    }

    @Test
    void 유저닉네임_빈값() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("닉네임은 필수 입력값입니다.");
    }

    @Test
    void 유저닉네임_중복() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );
        userService.registerUser(userRequestDto);
        UserRequestDto userRequestDto2 = new UserRequestDto(
                "test12345",
                "이름",
                "1q2w3e4r!",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto2);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("유저닉네임이 이미 존재합니다.");
    }


    @Test
    void 비밀번호_빈값() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "비밀",
                "",
                "1q2w3e4r!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("비밀번호는 필수 입력값입니다.");
    }

    @Test
    void 비밀번호_범위큼() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "비밀",
                "1q2w3e4r1q2w3e4r1q2w3e4r",
                "1q2w3e4r!1q2w3e4r1q2w3e4r",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("비밀번호는 영어,숫자,특수문자!@#$ 를 사용하여 6~15자로 입력해주세요.");
    }

    @Test
    void 비밀번호_범위작음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "비밀",
                "1q2w!",
                "1q2w!",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("비밀번호는 영어,숫자,특수문자!@#$ 를 사용하여 6~15자로 입력해주세요.");
    }

    @Test
    void 비밀번호_확인_불일치() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "비밀",
                "1q2w3e4r!",
                "1q2w3e4r",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }

    @Test
    void 비밀번호_확인_없음() {
        //given
        UserRequestDto userRequestDto = new UserRequestDto(
                "test1234",
                "비밀",
                "1q2w3e4r!",
                "",
                1L
        );

        //when
        CustomException customException = assertThrows(CustomException.class, () -> {
            userService.registerUser(userRequestDto);
        });

        //then
        assertThat(customException.getErrorCode().getErrorMessage()).isEqualTo("비밀번호 확인은 필수 입력값입니다.");
    }
}