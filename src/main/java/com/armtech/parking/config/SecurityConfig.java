package com.armtech.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF (adequado para APIs REST com autenticação sem estado)
                .authorizeHttpRequests(auth -> auth
                        // Permitir acesso público aos endpoints do Swagger UI e OpenAPI
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**").permitAll()
                        // Permitir recursos estáticos (JS, CSS, imagens, etc.)
                        .requestMatchers("/", "/csrf", "*.js", "*.css", "*.ico", "*.png").permitAll()
                        // Exigir autenticação para qualquer outro endpoint
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {
                }) // Habilita autenticação HTTP Basic
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sessão sem estado

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return (username) -> User
                .withUsername("user")
                .password(passwordEncoder.encode("User@123456"))
                .roles("USER")
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}