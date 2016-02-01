package com.zenquizz;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zenquizz.base.ConstantKt;
import com.zenquizz.web.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class WebConfig {

	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}

    @Autowired private AppConfig appConfig;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        objectMapper.setDateFormat(new SimpleDateFormat(ConstantKt.getMAIN_DATE_FORMAT()));
        return objectMapper;
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        String key = appConfig.getRememberMeKey();
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(key, userDetailsService);
        tokenBasedRememberMeServices.setAlwaysRemember(true);
        return tokenBasedRememberMeServices;
    }
}