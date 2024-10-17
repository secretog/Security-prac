package org.secret.usersec.service;

import org.secret.usersec.dto.UserCreateDto;
import org.secret.usersec.dto.UserReadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

     Page<UserReadDto> findAllUsers(Pageable pageable);

     Optional<UserReadDto> findUserById(Long id);

     UserReadDto createUser(UserCreateDto userCreateDto);

     boolean deleteUser(Long id);

     Optional<UserReadDto> updateUser(Long id, UserCreateDto userDto);
}