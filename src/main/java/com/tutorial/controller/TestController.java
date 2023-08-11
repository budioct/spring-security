package com.tutorial.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Test Authority

    @GetMapping(
            path = "/api/test",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String test(){
        return "Test!";
    }

    @GetMapping(
            path = "/api/demo",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String demo(){
        return "Demo!";
    }

    // read, write, delete, access, execute ---> authorities
    // ADMIN, MANAGER, USER, CLIENT, ADMINISTRATOR ---> roles
    // GrantedAuthority -----> something with a name
        // ROLE_ADMIN, ROLE_MANAGER, ROLE_USER


}
