package com.project.event_notification.security;

import com.project.event_notification.security.token.JwtTokenFilter;
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
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final AccessDeniedExceptionHandler deniedExceptionHandler;
    private final AuthenticationEntryPointHandler entryPointHandler;

    private final JwtTokenFilter tokenFilter;

    public SecurityConfiguration(AccessDeniedExceptionHandler deniedExceptionHandler, AuthenticationEntryPointHandler entryPointHandler, JwtTokenFilter tokenFilter) {
        this.deniedExceptionHandler = deniedExceptionHandler;
        this.entryPointHandler = entryPointHandler;
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityFilterChain security(
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
                .addFilterBefore(tokenFilter, AnonymousAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
