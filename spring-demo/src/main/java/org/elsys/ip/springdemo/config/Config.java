package org.elsys.ip.springdemo.config;

import org.elsys.ip.springdemo.logic.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Calculator calculator() {
        return new Calculator();
    }
}
