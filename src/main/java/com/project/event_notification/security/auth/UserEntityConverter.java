package com.project.event_notification.security.auth;

import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getAge(),
                UserRole.valueOf(userEntity.getRole())
        );
    }

//    public UserEntity toDomain(User user) {
//        return new User(
//                user.id(),
//                user.login(),
//                user.password(),
//                user.age(),
//                user.role(UserRole)
//        );
//    }
}
