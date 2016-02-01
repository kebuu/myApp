package com.zenquizz.web.security.bean;

import com.zenquizz.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SocialUserDetailsImpl implements SocialUserDetails, UserDetails {

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
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }

    public String getEmail() {
        return user.getEmail();
    }
    public String getDisplayName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getPassword() {
        return user.getEmail();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
