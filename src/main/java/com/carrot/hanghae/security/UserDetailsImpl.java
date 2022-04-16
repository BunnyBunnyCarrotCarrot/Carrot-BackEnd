package com.carrot.hanghae.security;

import com.carrot.hanghae.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

/*
    private final String userId;
    private final String userPw;

    public UserDetailsImpl(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }


    @Override
    public String getPassword() {
        return this.userPw();
    }

    @Override
    public String getUsername() {
        return this.userId();
    }

 */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

}
