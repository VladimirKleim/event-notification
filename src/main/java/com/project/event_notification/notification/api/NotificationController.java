package com.project.event_notification.notification.api;

import com.project.event_notification.notification.domain.NotificationDto;
import com.project.event_notification.notification.domain.NotificationDtoConverter;
import com.project.event_notification.notification.domain.NotificationListenerList;
import com.project.event_notification.notification.domain.NotificationService;
import com.project.event_notification.security.token.JwtTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    private final JwtTokenManager jwtTokenManager;

    private final NotificationDtoConverter dtoConverter;

    public NotificationController(NotificationService notificationService, JwtTokenManager jwtTokenManager, NotificationDtoConverter dtoConverter) {
        this.notificationService = notificationService;
        this.jwtTokenManager = jwtTokenManager;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllUnread(
          @RequestHeader("Authorization") String auth
    ) {
      String jwtToken = auth.substring(7);
      String login = jwtTokenManager.getIdFromToken(jwtToken);
      return ResponseEntity.status(HttpStatus.OK)
              .body(notificationService.getAllUnreadMessage(login)
                      .stream()
                      .map(dtoConverter::toDto)
                      .toList());
    }

    @PostMapping
    public ResponseEntity<Void> setReadAllNotifications(
            @RequestBody NotificationListenerList listenerList
    ) {
        log.info("...");
        notificationService.readAllNotifications(listenerList.notificationIds());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
