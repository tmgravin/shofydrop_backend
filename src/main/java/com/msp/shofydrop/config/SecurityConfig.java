package com.msp.shofydrop.config;

import com.msp.shofydrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    public UserService userService;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(registry -> {
//                    registry.requestMatchers("/home", "/register/**").permitAll();
//                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
//                    registry.requestMatchers("/user/**").hasRole("USER");
//                    registry.requestMatchers("/vendor/**").hasRole("VENDOR");
//                    registry.requestMatchers("/deliveryBoy/**").hasRole("DELIVERY_BOY");
//                    registry.requestMatchers("/editor/**").hasRole("EDITOR");
//                    registry.anyRequest().authenticated();
//                })
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//                .build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return (UserDetailsService) userService;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService((UserDetailsService) userService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//
//    }
}
