package com.jonjia.readinglist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public boolean authManager(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests()
        .requestMatchers("/").hasAnyRole("READER")
        .requestMatchers("/**").permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error=true");
    return true;
  }

}
