package com.example.irrigationsystem;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class ConfigProperties {
    @Value("${use.allowed-origin:#{null}}")
    private String allowedOrigin;
}
