package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.domain.entity.User;
import com.github.gilvangobbato.service.imp.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user);
    }
}
