package com.project.event_notification.security.auth;

public record User(
        Long id,
        String login,
        String password,
        Integer age,
        UserRole role
) {
}
