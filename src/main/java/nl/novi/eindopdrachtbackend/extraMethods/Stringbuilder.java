package nl.novi.eindopdrachtbackend.extraMethods;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Stringbuilder {

    public static ResponseEntity<Object> getStringbuilder(BindingResult bindingResult) {
        StringBuilder stringbuilder = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringbuilder.append(fieldError.getField() + ": ");
            stringbuilder.append(fieldError.getDefaultMessage());
            stringbuilder.append("\n");
        }
        return ResponseEntity.badRequest().body(stringbuilder.toString());
    }

}
