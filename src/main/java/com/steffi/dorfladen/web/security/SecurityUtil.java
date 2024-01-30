package com.steffi.dorfladen.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.steffi.dorfladen.web.rest.JWTAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
// @EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityUtil  {

    @Autowired
    private JWTAuthenticationEntryPoint entryPoint;

    @Bean
    JWTAuthenticationFilter authFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    } 

    @Value("${web.jwtSecretKey}")
    private String jwtSecretKey;

    @Bean
    public PasswortHashing passwortHashing() {
        return new PasswortHashing(jwtSecretKey);
    }
    

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(handling -> handling
                    .authenticationEntryPoint(entryPoint))
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login*").permitAll()
                    .requestMatchers("/benutzer*").permitAll()
                    .anyRequest().authenticated())
            .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
