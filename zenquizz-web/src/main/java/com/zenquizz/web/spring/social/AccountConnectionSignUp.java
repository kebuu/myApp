package com.zenquizz.web.spring.social;

import com.zenquizz.dao.model.User;
import com.zenquizz.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class AccountConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private final UserRepository accountRepository;

    public AccountConnectionSignUp(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        User account = new User();
        accountRepository.insert(account);
        return account.getEmail();
    }

}