package org.secret.usersec.dto;

import lombok.Value;

@Value
public class UserLoginDto {
    String email;
    String password;
}
