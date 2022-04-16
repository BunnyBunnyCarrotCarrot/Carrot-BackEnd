package com.carrot.hanghae.security;


import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service         //로그인 요청이 왔을때 , 실행이된다.
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + userId));

        return new UserDetailsImpl(user);
    }
}