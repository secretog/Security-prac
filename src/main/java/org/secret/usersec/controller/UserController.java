package org.secret.usersec.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.secret.usersec.dto.UserCreateDto;
import org.secret.usersec.dto.UserReadDto;
import org.secret.usersec.exception.UserNotFoundException;
import org.secret.usersec.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserReadDto> getAllUsers(Pageable pageable) {
        log.info("Fetching all users");
        return userService.findAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public UserReadDto getUserById(@PathVariable Long id) {
        log.info("Get user with ID: {}", id);
        return userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/register")
    public UserReadDto saveUser(@RequestBody UserCreateDto userCreateDto) {
        log.info("Register user: {}", userCreateDto);
        return userService.createUser(userCreateDto);
    }

    @PutMapping("/{id}")
    public UserReadDto updateUser(@PathVariable Long id, @RequestBody UserCreateDto userCreateDto) {
        log.info("Updating user with ID: {}", id);
        return userService.updateUser(id, userCreateDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        if (!userService.deleteUser(id)) {
            throw new UserNotFoundException(id);
        }
    }
}
