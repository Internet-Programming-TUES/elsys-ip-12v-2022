package org.elsys.ip.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("user1")
//          .password(passwordEncoder.encode("user1Pass"))
//          .authorities("ROLE_USER");

//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("user2");
//        userEntity.setPasswordHash(passwordEncoder.encode("pass"));
//        userRepository.save(userEntity);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/h2-console/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .authenticationEntryPoint(authenticationEntryPoint);
        //http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);

        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}