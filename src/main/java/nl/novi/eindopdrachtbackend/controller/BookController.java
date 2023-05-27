package nl.novi.eindopdrachtbackend.controller;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
public class BookController {

    private BookService bookService;
    private BookRepository bookRepository;

    // INJECTION //
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Object> addTeacher(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {
        //bindingResult test het resultaat en mogelijke errors
        if (bindingResult.hasFieldErrors()) {
            //Stringbuilder aangemaakt die de message gaat samenvoegen
            StringBuilder stringbuilder = new StringBuilder();
            // Wanneer er een fieldError is, wordt deze in een message aan de gebruiker teruggegeven
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                stringbuilder.append(fieldError.getField() + ": ");
                stringbuilder.append(fieldError.getDefaultMessage());
                stringbuilder.append("\n");
            }
            return ResponseEntity.badRequest().body(stringbuilder.toString());
        } else {
            // als er geen fieldErrors zijn, gaan we door naar deze return
            // Er komt een link naar de servicelaag
            String bookinfo = bookService.createBook(bookDto);

            //Vangt velden op en gebruikt die om de response header te vullen
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + bookinfo).toUriString());

            // Geeft een response van de nieuwe ID aan de client
            return ResponseEntity.created(uri).body(bookinfo);
        }
    }

    }

//    public ResponseEntity<Object> stringBuilderMethod(BindingResult bindingResult) {
//        // Er wordt een Stringbuilder aangemaakt die de message gaat samenvoegen //
//        StringBuilder stringBuilder = new StringBuilder();
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            stringBuilder.append(fieldError.getField() + ": ");
//            stringBuilder.append(fieldError.getDefaultMessage());
//            stringBuilder.append("\n");
//        }
//        return ResponseEntity.badRequest().body(stringBuilder.toString());
//    }



