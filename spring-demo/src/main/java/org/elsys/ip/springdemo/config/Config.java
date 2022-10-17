package org.elsys.ip.springdemo.config;

import org.elsys.ip.springdemo.logic.Calculator;
import org.elsys.ip.springdemo.web.CustomServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public ServletRegistrationBean exampleServletBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new CustomServlet(), "/servlet/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
