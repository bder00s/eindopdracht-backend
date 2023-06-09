package nl.novi.eindopdrachtbackend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    HttpSecurity httpSecurity;

//
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder,) throws Exception {
//
//    }
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
