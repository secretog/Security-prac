package org.secret.usersec.security.service;

import org.secret.usersec.database.entity.Role;
import org.secret.usersec.dto.UserCreateDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final String role;

    public MyUserDetails(UserCreateDto userCreateDto) {
        this.email = userCreateDto.getEmail();
        this.password = userCreateDto.getPassword();
        this.role = userCreateDto.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(Role.valueOf(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
