package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.api.dto.CredentialsDTO;
import com.github.gilvangobbato.api.dto.TokenDTO;
import com.github.gilvangobbato.domain.entity.User;
import com.github.gilvangobbato.exception.SenhaInvalidaException;
import com.github.gilvangobbato.service.imp.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody CredentialsDTO credentials){
        try{
            userService.auth(User.builder()
            .username(credentials.getUsername())
            .password(credentials.getPassword()).build());
        }catch (UsernameNotFoundException ex){
            return null;
        } catch (SenhaInvalidaException ex){
            return null;
        }
    }

}
