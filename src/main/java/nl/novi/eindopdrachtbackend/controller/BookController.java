package nl.novi.eindopdrachtbackend.controller;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.enummeration.Genre;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.service.BookService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static nl.novi.eindopdrachtbackend.extraMethods.Stringbuilder.getStringbuilder;


@RestController
@RequestMapping("/books")
public class BookController {


    private BookService bookService;
    private BookRepository bookRepository;

    // INJECTION //
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping
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

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long isbn, @Valid @RequestBody BookDto bookDto) throws BookNotFoundException {
        bookService.updateBook(isbn, bookDto);
        return ResponseEntity.ok().body(bookDto);
    }



    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {

      List<BookDto> bookDtos = bookService.getAllBooks();
        return ResponseEntity.ok().body(bookDtos);
    }

    @GetMapping("/author")
    public ResponseEntity<ArrayList<BookDto>> getBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok().body(bookService.findBooksByAuthor(author));
    }

    @GetMapping("/title")
    public ResponseEntity<ArrayList<BookDto>> getBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok().body(bookService.findBooksByTitle(title));
    }

    @GetMapping("/genre")
    public ResponseEntity<ArrayList<BookDto>> getBooksByGenre(@RequestParam Genre genre) {
        return ResponseEntity.ok().body(bookService.findBooksByGenre(genre));
    }

    @DeleteMapping("/{isbn}")
    public String deleteBook(@PathVariable Long isbn) throws BookNotFoundException {
        bookService.deleteBook(isbn);
        return "Book with isbn " + isbn + " succesfully deleted!";
    }


}





