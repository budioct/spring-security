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


}
