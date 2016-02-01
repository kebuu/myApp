package com.zenquizz.web;

import com.zenquizz.dao.DbTestConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DbTestConfig.class})
public class WebTestConfig {
}
