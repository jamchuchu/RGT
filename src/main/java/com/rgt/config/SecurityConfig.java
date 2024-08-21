package com.rgt.config;
import com.rgt.config.filter.JwtAuthorizationFilter;
import com.rgt.constants.Authority;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity

public class SecurityConfig {

//    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public JwtAuthorizationFilter jwtAuthorizationFilter() {
//        return new JwtAuthorizationFilter( jwtService, userDetailsService);
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtService);
//        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
//        return filter;
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
//
                .sessionManagement(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
                .authorizeHttpRequests((authorizationRequests) -> authorizationRequests
//                                .anyRequest().permitAll()

                                .requestMatchers("/api/user/login", "/api/user/signup").permitAll()
                                .requestMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .requestMatchers("/test").hasAuthority(Authority.USER.getAuthority())
//                        .anyRequest().authenticated()
                )

//        // 필터 관리
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

