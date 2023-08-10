package com.tutorial.controller;

import com.tutorial.dto.CreateUserRequest;
import com.tutorial.dto.UserResponse;
import com.tutorial.dto.WebResponse;
import com.tutorial.service.UsersDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UsersDetailServices usersDetailServices;

    @PostMapping(
            path = "/api/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> createUser(@RequestBody CreateUserRequest request){

        UserResponse userResponse = usersDetailServices.createUser(request);

        return WebResponse.<UserResponse>builder()
                .data(userResponse)
                .build();

    }



}
