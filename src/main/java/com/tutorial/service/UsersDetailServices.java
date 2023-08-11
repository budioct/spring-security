package com.tutorial.service;

import com.tutorial.dto.CreateUserRequest;
import com.tutorial.dto.UserResponse;
import com.tutorial.entity.User;
import com.tutorial.repository.UserRepository;
import com.tutorial.security.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UsersDetailServices implements UserDetailsService {

    @Autowired
    ValidationService validationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usrnm = userRepository.findFirstByUsername(username);

        User user = usrnm.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        //log.info("loadUserByUsername=== {}", user);

        return new SecurityUser(user);

    }

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        validationService.validate(request);

        User user = new User();
        user.setUsername(request.getUsername());

        Example<User> example = Example.of(user);
        if (userRepository.exists(example)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already created");
        }

        //user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user); // Save DB

        return toUserResponse(user);

    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

    }

}
