package nl.novi.eindopdrachtbackend.dto;

// JWT TOKEN DIE TERUG KOMT NA AUTHENTICATIE

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
