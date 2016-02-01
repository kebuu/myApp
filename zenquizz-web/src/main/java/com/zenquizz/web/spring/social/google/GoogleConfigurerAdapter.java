package com.zenquizz.web.spring.social.google;

import com.zenquizz.dao.repository.UserRepository;
import com.zenquizz.web.spring.social.AccountConnectionSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.SocialAuthenticationToken;

@Configuration
@EnableConfigurationProperties(GoogleProperties.class)
public class GoogleConfigurerAdapter extends SocialConfigurerAdapter {

    @Autowired private GoogleProperties properties;
    @Autowired private UserRepository userRepository;

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Google google(ConnectionRepository repository) {
        Connection<Google> connection = repository.findPrimaryConnection(Google.class);
        if (connection == null && SecurityContextHolder.getContext().getAuthentication() instanceof SocialAuthenticationToken) {
            SocialAuthenticationToken authentication = (SocialAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            connection = (Connection<Google>) authentication.getConnection();
        }
        return connection != null ? connection.getApi() : null;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        InMemoryUsersConnectionRepository inMemoryUsersConnectionRepository = new InMemoryUsersConnectionRepository(connectionFactoryLocator);
        inMemoryUsersConnectionRepository.setConnectionSignUp(new AccountConnectionSignUp(userRepository));
        return inMemoryUsersConnectionRepository;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        GoogleConnectionFactory factory = new GoogleConnectionFactory(this.properties.getAppId(), this.properties.getAppSecret());
        factory.setScope("email profile");
        configurer.addConnectionFactory(factory);
    }
}