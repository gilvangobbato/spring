package com.github.gilvangobbato.service.imp;

import com.github.gilvangobbato.domain.entity.User;
import com.github.gilvangobbato.domain.repository.UserRepository;
import com.github.gilvangobbato.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado"));

        String[] role = user.isAdmin()?new String[]{"ADMIN", "USER"}:new String[]{ "USER"};
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(role)
                .build();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public UserDetails auth(User user){
        UserDetails details = loadUserByUsername(user.getUsername());
        if(passwordEncoder.matches(user.getPassword(), details.getPassword())){
            return details;
        }
        throw new SenhaInvalidaException();
    }
}
