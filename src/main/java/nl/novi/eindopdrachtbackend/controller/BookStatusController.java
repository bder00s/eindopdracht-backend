package nl.novi.eindopdrachtbackend.controller;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtbackend.dto.BookStatusDto;
import nl.novi.eindopdrachtbackend.service.BookStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static nl.novi.eindopdrachtbackend.extraMethods.Stringbuilder.getStringbuilder;

@RestController
public class BookStatusController {

    private BookStatusService bookStatusService;

    // INJECTION //
    public BookStatusController(BookStatusService bookStatusService) {
        this.bookStatusService = bookStatusService;
    }


    @PostMapping("/addBookStatus")
    public ResponseEntity<Object> addBookStatus(@Valid @RequestBody BookStatusDto bookStatusDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return getStringbuilder(bindingResult);
        } else {
            String bookStatusInfo = bookStatusService.createBookStatus(bookStatusDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + bookStatusInfo).toUriString());

            return ResponseEntity.created(uri).body(bookStatusInfo);
        }
    }


}
