package org.elsys.ip.springdemo.config;

import org.elsys.ip.springdemo.logic.Calculator;
import org.elsys.ip.springdemo.web.CalculatorServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    private ApplicationContext context;

    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public ServletRegistrationBean exampleServletBean(CalculatorServlet calculatorServlet) {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                calculatorServlet, "/calculator/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
