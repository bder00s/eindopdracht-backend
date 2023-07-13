package nl.novi.eindopdrachtbackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public UserNameNotFoundException(String username) {

        super("Cannot find user " + username);

    }

}


