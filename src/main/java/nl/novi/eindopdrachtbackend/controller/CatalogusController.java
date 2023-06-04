package nl.novi.eindopdrachtbackend.controller;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.CatalogusRepository;
import nl.novi.eindopdrachtbackend.service.BookService;
import nl.novi.eindopdrachtbackend.service.CatalogusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalogus")
public class CatalogusController {

    private CatalogusService catalogusService;
    private CatalogusRepository catalogusRepository;
    private BookService bookService;
    private BookRepository bookRepository;



    @GetMapping("/author")
    public ResponseEntity<ArrayList<BookDto>> getBooksByAuthor(@RequestParam String author){
        return ResponseEntity.ok().body(catalogusService.findBooksByAuthor(author));
    }


}
