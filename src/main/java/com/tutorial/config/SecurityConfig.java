package com.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) // apply security global level method // depreceted // only pre-post
@Configuration
@EnableMethodSecurity // apply security global level method
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
        http.httpBasic(); // HttpBasicConfigurer<HttpSecurity> httpBasic() // on version spring-boot: 3.1.0 this Depreceted since 6.1 spring-security
        http.httpBasic(Customizer.withDefaults()); // custume with this

        // remember
        //http.rememberMe().tokenRepository();

        http.authorizeHttpRequests()
                .requestMatchers("/api/user").permitAll() // jika user akses service /api/user maka akan di izinkan semua
//                .requestMatchers("/api/test").hasAuthority("read") // apply the authority basic
//                .requestMatchers("/api/demo").hasAuthority("write")
                .requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("read") // apply the authority with specification
                .requestMatchers("/smth").access(new WebExpressionAuthorizationManager("isAuthenticated()")) // apply the authorization at the method level
                .anyRequest().authenticated();

        // authorizeHttpRequests v lambda
        //http.authorizeHttpRequests(
//                authorizationManagerRequestMatcherRegistry -> {
//                    authorizationManagerRequestMatcherRegistry
//                            .requestMatchers("/api/user").permitAll()
//                            .requestMatchers(HttpMethod.GET,"/api/**").hasAuthority("read")
//                            .requestMatchers("/smth").access(new WebExpressionAuthorizationManager("isAuthenticated()"))
//                            .anyRequest().authenticated();
//                }
//        );

        // ignore endpoint register
        http.csrf().ignoringRequestMatchers("/api/user");

        // cors v lambda
        http.cors(
                c -> c.configurationSource(req -> {
                    CorsConfiguration conf = new CorsConfiguration();
                    conf.setAllowedMethods(List.of("*"));
                    return conf;
                })
        );

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
