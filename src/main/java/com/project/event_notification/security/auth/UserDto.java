package com.project.event_notification.security.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotNull
        Long id,
        @NotBlank
        String login,
        @NotNull
        Integer age,
        UserRole role
) {
}
