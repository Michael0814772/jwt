package com.jwt.jwt.service;

import com.jwt.jwt.dtos.LoginUserDto;
import com.jwt.jwt.dtos.RegisterUserDto;
import com.jwt.jwt.model.User;
import com.jwt.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RegisterUserDto input) {
        log.info("sign up method...");
        log.info("request: " + input);

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        log.info("user: " + user);

        user = userRepository.save(user);

        log.info("saved model: " + user);

        return user;
    }

    public User authenticate(LoginUserDto input) {
        log.info("authenticate method...");
        log.info("request: " + input);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
