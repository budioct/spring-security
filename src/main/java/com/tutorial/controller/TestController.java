package com.tutorial.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * // user security layer service theere annotation
     * @PreAuthorize
     * @PostAuthorize
     * @PreFilter
     * @PostFilter
     */

    // Test Authority

    //@PreAuthorize("hasAnyAuthority('read')")
    @GetMapping(
            path = "/api/test/{smth}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("@testEndpointAuthorizationManager.authorize(#TestEndpointAuthorizationManager.#Type.#A, #smth)") // costumes authorize
    public String test(@PathVariable(name = "smth") String smth){
        return "Test! " + smth;
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
