package com.project.event_notification.security.auth;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserEntityConverter entityConverter;

    public UserService(UserRepository userRepository, UserEntityConverter entityConverter) {
        this.userRepository = userRepository;
        this.entityConverter = entityConverter;
    }

    public User getUserById(String login) {
        return entityConverter.toDomain(
                userRepository.findByLogin(login).orElseThrow(() ->
                        new EntityNotFoundException("User with login %s not found".formatted(login))));
    }
}
