package com.zenquizz.web.controller;

import com.zenquizz.web.security.bean.SocialUserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class SecuredController {

    protected SocialUserDetailsImpl getAuthenticatedUser() {
        return (SocialUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}