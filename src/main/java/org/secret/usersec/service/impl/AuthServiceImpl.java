package org.secret.usersec.service.impl;

import lombok.RequiredArgsConstructor;
import org.secret.usersec.dto.UserLoginDto;
import org.secret.usersec.service.AuthService;
import org.secret.usersec.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(UserLoginDto userLoginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),
                            userLoginDto.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authenticate);
            if (authenticate.isAuthenticated()) {
                return jwtService.generateToken(userLoginDto.getEmail());
            }
            return "Login successful";
        } catch (
                BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credential");
        }
    }
}
