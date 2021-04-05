package com.server.model.entities;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority()
    {
        return name();
    }
}
