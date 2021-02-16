package ru.skillsad.sad.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name(); //ToString() - тоже подходит
    }
}
