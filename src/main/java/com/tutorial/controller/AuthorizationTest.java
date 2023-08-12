package com.tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
public class AuthorizationTest {

    /**
     * // user security layer service theere annotation
     * @PreAuthorize
     * @PostAuthorize
     * @PreFilter
     * @PostFilter
     */

    @GetMapping(path = "/api/test1")
    @PreAuthorize("hasAnyAuthority('read')")
    public String test1(){

        log.info(":)");
        return "Test 1";
    }

    @GetMapping(path = "/api/test2")
    @PostAuthorize("hasAnyAuthority('read')")
    public String test2(){

        log.info(":(");
        return "Test 2";
    }

    @GetMapping(path = "/api/test3")
    @PreFilter("filterObject.contains('a')")
    public void test3(@RequestBody List<String> values){

        values.forEach(System.out::println); // in the console only words conatining the letter a will be printed

    }

    @GetMapping(path = "/api/test4")
    @PostFilter("filterObject.contains('a')")
    public List<String> test4(){

        List<String> names = new LinkedList<>();
        names.add("budhi");
        names.add("juana");
        names.add("mamat");

//            List<String> list = List.of("budhi", "juana", "mamat"); //  immutable -> doesn't work

        return names;

    }

}
