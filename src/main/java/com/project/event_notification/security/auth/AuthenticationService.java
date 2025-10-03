package com.project.event_notification.security.auth;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private final UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);



    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public User authenticateUser(String login) {
        log.info("Received authentication user with login: {}", login);
        var user = userRepository.findByLogin(login).orElseThrow(() ->
                new EntityNotFoundException("User with login: %s don't exist".formatted(login)));
        return new User(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getAge(),
                UserRole.valueOf(user.getRole())
        );
    }

}
