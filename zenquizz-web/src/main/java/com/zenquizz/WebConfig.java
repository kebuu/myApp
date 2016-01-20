package com.zenquizz;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zenquizz.base.ConstantKt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@SpringBootApplication
@Configuration
public class WebConfig {

	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class, args);
	}

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        objectMapper.setDateFormat(new SimpleDateFormat(ConstantKt.getMAIN_DATE_FORMAT()));
        return objectMapper;
    }
}