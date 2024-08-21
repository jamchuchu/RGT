package com.rgt.constants;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    OWNER("ROLE_OWNER"),
    USER("ROLE_USER");

    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}