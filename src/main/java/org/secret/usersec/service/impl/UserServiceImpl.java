package org.secret.usersec.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.secret.usersec.database.entity.Role;
import org.secret.usersec.database.repository.UserRepository;
import org.secret.usersec.dto.UserCreateDto;
import org.secret.usersec.dto.UserReadDto;
import org.secret.usersec.mapper.UserCreateMapper;
import org.secret.usersec.mapper.UserReadMapper;
import org.secret.usersec.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public Page<UserReadDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userReadMapper::toDto);
    }

    public Optional<UserReadDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::toDto);
    }

    @Transactional
    public UserReadDto createUser(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userCreateMapper::toEntity)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    if (userCreateDto.getRole().equalsIgnoreCase("ADMIN")) {
                        user.setRole(Role.ADMIN);
                    } else {
                        user.setRole(Role.USER);
                    }
                    return user;
                })
                .map(userRepository::save)
                .map(userReadMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public Optional<UserReadDto> updateUser(Long id, UserCreateDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(userDto.getFirstName());
                    user.setLastName(userDto.getLastName());
                    user.setEmail(userDto.getEmail());
                    if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    }
                    if (userDto.getRole() != null) {
                        user.setRole(Role.valueOf(userDto.getRole().toUpperCase()));
                    }
                    return userRepository.saveAndFlush(user);
                })
                .map(userReadMapper::toDto);
    }
}