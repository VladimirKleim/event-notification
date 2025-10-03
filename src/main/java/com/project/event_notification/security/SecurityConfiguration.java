package com.project.event_notification.security;

import com.project.event_notification.web.AccessDeniedExceptionHandler;
import com.project.event_notification.web.AuthenticationEntryPointHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final AccessDeniedExceptionHandler deniedExceptionHandler;
    private final AuthenticationEntryPointHandler entryPointHandler;

    public SecurityConfiguration(AccessDeniedExceptionHandler deniedExceptionHandler, AuthenticationEntryPointHandler entryPointHandler) {
        this.deniedExceptionHandler = deniedExceptionHandler;
        this.entryPointHandler = entryPointHandler;
    }

    public SecurityFilterChain securityConfiguration(
            HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize

                                .requestMatchers(HttpMethod.POST,"/notification").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET, "/notification").hasAnyAuthority("ADMIN", "USER")


                                .anyRequest().authenticated()
                )

                .exceptionHandling(exception ->
                        exception.accessDeniedHandler(deniedExceptionHandler)
                        .authenticationEntryPoint(entryPointHandler))
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
