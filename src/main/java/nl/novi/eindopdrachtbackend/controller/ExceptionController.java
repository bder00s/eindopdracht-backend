package nl.novi.eindopdrachtbackend.controller;

import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice //voor exception controllers altijd deze annotatie gebruiken
public class ExceptionController {
        /// NOT FOUND ///////////
        @ExceptionHandler(value = BookNotFoundException.class)
        public ResponseEntity<Object> exception(BookNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
}
