package nl.novi.eindopdrachtbackend.controller;


import nl.novi.eindopdrachtbackend.dto.AuthenticationDto;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

private AuthenticationManager authenticationManager;
//private JwtService


    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthenticationDto authenticationDto){
        UsernamePasswordAuthenticationToken usernameAndPassword =
                new UsernamePasswordAuthenticationToken(authenticationDto.username, authenticationDto.password);

        try {
            Authentication authentication = authenticationManager.authenticate(usernameAndPassword);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String token = jwtService.generateToken(usernameAndPassword);

    }
}
