package com.zenquizz.dao.utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public abstract class TestUtils {

    @SneakyThrows
    public static String getResourceAsString(String resourcePath) {
        return FileUtils.readFileToString(new ClassPathResource(resourcePath).getFile());
    }
}
