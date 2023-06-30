package nl.novi.eindopdrachtbackend.security;


import nl.novi.eindopdrachtbackend.filter.JwtRequestFilter;
import nl.novi.eindopdrachtbackend.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*  Deze security is niet de enige manier om het te doen.
    In de andere branch van deze github repo staat een ander voorbeeld
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }


    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        //Jwt token authentication
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()
                // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.

                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/{username}/authorities").hasRole("STAFF")
                .requestMatchers(HttpMethod.POST, "/users/newuser").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("STAFF", "MEMBER")
                .requestMatchers(HttpMethod.GET, "/books").hasAnyRole("STAFF", "MEMBER")
                .requestMatchers(HttpMethod.POST, "/books").hasRole("STAFF")
                .requestMatchers(HttpMethod.PUT, "/books/{isbn}").hasRole("STAFF")
                .requestMatchers(HttpMethod.DELETE, "/books/{isbn}").hasRole("STAFF")
                .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("STAFF", "MEMBER")



//                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("STAFF")


                // Je mag meerdere paths tegelijk definieren

                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()
                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}