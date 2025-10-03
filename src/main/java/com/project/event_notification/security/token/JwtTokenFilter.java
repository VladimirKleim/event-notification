package com.project.event_notification.security.token;

import com.project.event_notification.security.auth.UserRepository;
import com.project.event_notification.security.auth.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager tokenManager;

    private final UserRepository userRepository;


    public JwtTokenFilter(JwtTokenManager tokenManager, UserRepository userRepository) {
        this.tokenManager = tokenManager;
        this.userRepository = userRepository;

    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

       var authenticated = request.getHeader(HttpHeaders.AUTHORIZATION);
       if (authenticated == null || !authenticated.startsWith("Bearer ")) {
           filterChain.doFilter(request,response);
           return;
       }

       var jwtToken = authenticated.substring(7);
       if (!tokenManager.isTokenValid(jwtToken)) {
           filterChain.doFilter(request,response);
           return;
       }


       var login = tokenManager.getLoginFromToken(jwtToken);
       var user = userRepository.findByLogin(login);
       var role = tokenManager.getRoleFromToken(jwtToken);

       UsernamePasswordAuthenticationToken token =
               new UsernamePasswordAuthenticationToken(
                       user,
                       null,
                       List.of(new SimpleGrantedAuthority(role))
               );

       SecurityContextHolder.getContext().setAuthentication(token);
       filterChain.doFilter(request,response);
    }
}
