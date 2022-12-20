package com.block7.block7crudvalidation.security.authorities;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

public record BaseGrantedAuthority(@Getter String role) implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof BaseGrantedAuthority && role.equals(((BaseGrantedAuthority) obj).role);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return this.role;
    }
}
