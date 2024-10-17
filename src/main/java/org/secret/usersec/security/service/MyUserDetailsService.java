package org.secret.usersec.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.secret.usersec.database.repository.UserRepository;
import org.secret.usersec.mapper.UserCreateMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by email: {}", username);
        return userRepository.findUserByEmail(username)
                .map(userCreateMapper::toDto)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}












