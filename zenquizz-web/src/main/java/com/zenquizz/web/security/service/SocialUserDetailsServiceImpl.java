package com.zenquizz.web.security.service;

import com.zenquizz.dao.model.User;
import com.zenquizz.dao.repository.UserRepository;
import com.zenquizz.web.security.bean.SocialUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        SocialUserDetails socialUserDetails = null;
        User user = userRepository.findByEmail(userId);

        if (user != null) {
            socialUserDetails = new SocialUserDetailsImpl(user);
        }

        return socialUserDetails;
    }
}
