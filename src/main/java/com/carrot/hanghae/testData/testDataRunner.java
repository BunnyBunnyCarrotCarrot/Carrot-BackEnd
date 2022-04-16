package com.carrot.hanghae.testData;

import com.carrot.hanghae.domain.Category;
import com.carrot.hanghae.domain.Location;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.repository.CategoryRepository;
import com.carrot.hanghae.repository.LocationRepository;
import com.carrot.hanghae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class testDataRunner implements ApplicationRunner {


    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 테스트 User 생성
        Location location = new Location(11L,"서울특별시");
        locationRepository.save(location);
        User user0= new User("test","1234", location);
        System.out.println("유저를 저장합니다.");
        userRepository.save(user0);

        Category category1 = new Category(1L, "디지털기기");
        Category category2 = new Category(2L, "주방용품");
        System.out.println("카테고리를 저장합니다.");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

//        Post post0 = new Post("안녕하세요1",user0);
//        Post post1 = new Post("안녕하세요2",user0);
//
//        postRepository.save(post0);
//        postRepository.save(post1);
    }
}
