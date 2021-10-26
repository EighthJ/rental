package com.rental.entity.User.dto;

import com.rental.domain.user.User;
import lombok.*;

public class userDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class create {
        private String name;
        private String email;
        private String nickname;
        private String password;
        private String address;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class login {
        private String email;
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class token {
        private String token;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Info {
        private Long id;
        private String name;
        private String email;
        private String nickname;
        private String role;
        private String address;
        private String userIntro;

        public Info(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.role = user.getRole().getKey();
            this.address = user.getAddress();
            this.userIntro = user.getUserIntro();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class update {
        private String name;
        private String nickname;
        private String address;
        private String userIntro;
    }
}

