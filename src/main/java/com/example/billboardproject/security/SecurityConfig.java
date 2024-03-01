package com.example.billboardproject.security;


import com.example.billboardproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);


        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        http.exceptionHandling().accessDeniedPage("/forbidden");
        http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll();


        http.formLogin()
                .loginProcessingUrl("/auth").permitAll()
                .defaultSuccessUrl("/mainPage")
                .failureUrl("/auth/?error")
                .usernameParameter("user_email")
                .passwordParameter("user_password")
                .loginPage("/").permitAll();

        http.logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/signOut");

        http.csrf().disable();

        return http.build();
    }


}
