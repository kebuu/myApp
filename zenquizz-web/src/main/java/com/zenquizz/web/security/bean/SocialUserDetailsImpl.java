package com.zenquizz.web.security.bean;

import com.zenquizz.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SocialUserDetailsImpl implements SocialUserDetails {

    private User user;

    public SocialUserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public String getUserId() {
        return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

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
}
