package com.zenquizz.web.spring.social;

import com.zenquizz.dao.model.User;
import com.zenquizz.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class AccountConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private final UserRepository userRepository;

    public AccountConnectionSignUp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        User user = userRepository.findByEmail(profile.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(profile.getEmail());
            user.setFirstName(profile.getFirstName());
            user.setLastName(profile.getLastName());
            userRepository.insert(user);
        }
        return user.getEmail();
    }

}