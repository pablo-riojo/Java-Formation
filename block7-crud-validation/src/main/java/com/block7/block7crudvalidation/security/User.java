package com.block7.block7crudvalidation.security;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.security.authorities.BaseGrantedAuthority;
import com.block7.block7crudvalidation.security.authorities.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class User implements UserDetails {
    @Getter
    private final Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<BaseGrantedAuthority> authorities = new ArrayList<>();
        if(person.getAdmin()) {
            authorities.add(new BaseGrantedAuthority(Role.ROLE_ADMIN.getAuthority()));
        } else {
            authorities.add(new BaseGrantedAuthority(Role.ROLE_USER.getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUser();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
