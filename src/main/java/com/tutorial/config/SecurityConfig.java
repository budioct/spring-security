package com.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * // configuration security
     * SecurityFilterChain menangani filter endpoint yang nantinya di teruskan ke DispatcherServlet manage jalur URI
     * UserDetailsService menangani authentication mengambil nama user dan password
     * PasswordEncoder implementasi generate parameter argument yang nantinya akan di encode menjadi encyrpt yang bisa di terima oleh filter spring security
     *
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // authentication
        //http.httpBasic().and()
        //        .authorizeHttpRequests()
        //        .anyRequest().authenticated();

        // authentication ver 2
        //http.httpBasic().and()
        //                .formLogin();

        // authorization
        //http.authorizeHttpRequests()
        //        .anyRequest().authenticated();

        // register
        http.httpBasic();

        http.authorizeHttpRequests()
                .requestMatchers("/api/user").permitAll()
//                .requestMatchers("/api/test").hasAuthority("read") // apply the authority basic
//                .requestMatchers("/api/demo").hasAuthority("write")
                .requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("read") // apply the authority with specification
                .anyRequest().authenticated();

        // ignore endpoint register
        http.csrf().ignoringRequestMatchers("/api/user");

        return http.build();

    }

//    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.builder()
                .username("budhi")
                .password(passwordEncoder().encode("password"))
                .authorities("read") // used for authorization
                .build();

        return new InMemoryUserDetailsManager(user);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        // return new SCryptPasswordEncoder();
        // return new Pbkdf2PasswordEncoder();
        return new BCryptPasswordEncoder(); // implement BCripty for char password

    }




}
