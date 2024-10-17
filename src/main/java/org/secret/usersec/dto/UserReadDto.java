package org.secret.usersec.dto;

import lombok.Value;

@Value
public class UserReadDto {
    String firstName;
    String lastName;
    String email;
    String role;
}
