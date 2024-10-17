package org.secret.usersec.dto;

import lombok.Value;

@Value
public class UserCreateDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String role;
}
