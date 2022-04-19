package com.carrot.hanghae.testdata;


import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.repository.LocationRepository;
import com.carrot.hanghae.repository.UserRepository;
import com.carrot.hanghae.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
// 테스트 User 생성
        // 테스트 User 의 관심상품 등록
        // 검색어 당 관심상품 10개 등록
//        createTestData(1L, "서울");
//        createTestData(2L, "인천");
//        createTestData(3L, "부산");
//        createTestData(4L, "경기");
//        Location location = locationRepository.findById(1L).orElseThrow(
//                () -> new IllegalArgumentException("잘못된 접근입니다.")
//        );
//        User testUser = new User("슈가", passwordEncoder.encode("123"), location);
//        testUser = userRepository.save(testUser);

    }

    private void createTestData(Long locationId, String locationName) throws IOException {
        Location location = new Location(locationId, locationName);
        locationRepository.save(location);
    }
}