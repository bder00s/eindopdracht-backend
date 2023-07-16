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

                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()
                // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.


                .requestMatchers(HttpMethod.POST, "/users/newuser").permitAll()
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .requestMatchers(HttpMethod.GET, "/users/allusers").hasRole("STAFF")
                .requestMatchers(HttpMethod.GET, "/users/{username}").hasAnyRole("STAFF", "MEMBER")
                .requestMatchers(HttpMethod.PUT, "/users/{username}").hasAnyRole("STAFF", "MEMBER") // FIXEN DAT OUTDATED USER NIET GEKLOOND WORDT
                .requestMatchers(HttpMethod.DELETE, "/users/{username}").hasRole("STAFF")

                .requestMatchers(HttpMethod.GET, "/users/{username}/authorities").hasRole("STAFF")
                .requestMatchers(HttpMethod.POST, "/users/{username}/authorities").hasRole("STAFF")
                .requestMatchers(HttpMethod.DELETE, "/users/{username}/authorities/{authority}").hasRole("STAFF")


                .requestMatchers(HttpMethod.POST, "/books").hasRole("STAFF")
                .requestMatchers(HttpMethod.PUT, "/books/{isbn}").hasRole("STAFF") // FIXEN DAT OUTDATED BOEK NIET GEKLOOND WORDT
                .requestMatchers(HttpMethod.DELETE, "/books/{isbn}").hasRole("STAFF")
                .requestMatchers(HttpMethod.GET, "/books/all").hasAnyRole("STAFF", "MEMBER")
                .requestMatchers(HttpMethod.GET, "/books/author").hasAnyRole("STAFF", "MEMBER")
                .requestMatchers(HttpMethod.GET, "books/title").hasAnyRole("STAFF", "MEMBER")
                .requestMatchers(HttpMethod.GET, "/books/genre").hasAnyRole("STAFF", "MEMBER")


                // Je mag meerdere paths tegelijk definieren

                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}