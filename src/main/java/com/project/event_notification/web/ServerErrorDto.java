package com.project.event_notification.web;

import java.time.OffsetDateTime;

public record ServerErrorDto(
        String message,
        String detailedMessage,
        OffsetDateTime dateTime
) {
}
