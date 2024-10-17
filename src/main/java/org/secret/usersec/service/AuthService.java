package org.secret.usersec.service;

import org.secret.usersec.dto.UserLoginDto;

public interface AuthService {
    String login(UserLoginDto userLoginDto);
}
