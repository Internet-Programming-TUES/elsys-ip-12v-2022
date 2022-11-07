package org.elsys.ip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GuessGameApplication {

    @Bean
    public ServletRegistrationBean exampleServletBean(GuessGameServlet servlet) {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                servlet, "/game/*");
        bean.setLoadOnStartup(1);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(GuessGameApplication.class, args);
    }

}
