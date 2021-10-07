package com.rest.live.domain.user;

import lombok.Getter;

@Getter
public class LoginResponseUser {

    private String name;
    private String email;
    private String phone;
    private String role;

    public LoginResponseUser(User entity) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.role = entity.getRole().getTitle();
    }
}
