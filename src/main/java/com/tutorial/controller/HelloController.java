package com.tutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * pada spring security saat setiap endpoint / service akan di bungkus dengan security
     * jika user belum authorized/login makan akan ==> Response: status_code: 401, staus: Unauthorized
     *
     * jadi untuk bisa mengakses setiap servicenya kita harus menyertakan token yang sudah di generate oleh app spring boot, seperti contoh di bawah
     *
     * Using generated security password: 7297b750-1c0c-41a1-b77e-c0a4daeac698
     * This generated password is for development use only. Your security configuration must be updated before running your application in production.
     *
     * jika ingin mengujinya di post man..
     * pilih navBar --> Authorization --> Type: Basic Auth --> username: user, password: password dari generate app spring boot
     * maka ednpoint/ service yang kita request akan bisa memberikan response yang di inginkan
     *
     */

    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello World!";
    }


    /**
     * // untuk http method berbeda dengan cara http method get. walaupun kita sudah melakukan authorization respon akan Unauthorization
     * maka dari itu spring security telah menyediakan request http method post kita bisa menggunakan unit test yang sudah di sediakan
     */

    @PostMapping(path = "/demo")
    public String demo(){
        return "Demo!";
    }



}
