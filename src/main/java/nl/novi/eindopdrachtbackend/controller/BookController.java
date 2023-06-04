package nl.novi.eindopdrachtbackend.controller;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static nl.novi.eindopdrachtbackend.extraMethods.Stringbuilder.getStringbuilder;


@RestController
@RequestMapping("/book")
public class BookController {


    private BookService bookService;
    private BookRepository bookRepository;

    // INJECTION //
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/newBook")
    public ResponseEntity<Object> addBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {
        //bindingResult test het resultaat en mogelijke errors
        if (bindingResult.hasFieldErrors()) {
            //Stringbuilder aangemaakt die de message gaat samenvoegen
            return getStringbuilder(bindingResult);
        } else {
            // als er geen fieldErrors zijn, gaan we door naar deze return
            // Er komt een link naar de servicelaag
            String bookinfo = bookService.newBook(bookDto);

            //Vangt velden op en gebruikt die om de response header te vullen
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + bookinfo).toUriString());

            // Geeft een response van de nieuwe ID aan de client
            return ResponseEntity.created(uri).body(bookinfo);
        }
    }

    @PutMapping("/updateBook/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long isbn, @Valid @RequestBody BookDto bookDto) throws BookNotFoundException {
        bookService.updateBook(isbn, bookDto);
        return ResponseEntity.ok().body(bookDto);
    }

    @PutMapping("/newBookstatus/{isbn}")
    public ResponseEntity<BookDto> newBookstatus(@PathVariable Long isbn, @RequestBody BookDto bookDto) throws BookNotFoundException {
        bookService.newBookStatus(isbn, bookDto);
        return ResponseEntity.ok().body(bookDto);
    }

    @DeleteMapping("/deleteBook/{isbn}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long isbn) throws BookNotFoundException {
        bookService.deleteBook(isbn);
        return ResponseEntity.ok().build();
    }


}





