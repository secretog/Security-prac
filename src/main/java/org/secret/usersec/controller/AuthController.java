package org.secret.usersec.controller;

import lombok.RequiredArgsConstructor;
import org.secret.usersec.dto.UserLoginDto;
import org.secret.usersec.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        var login = authService.login(userLoginDto);
        return ResponseEntity.ok(login);
    }
}
