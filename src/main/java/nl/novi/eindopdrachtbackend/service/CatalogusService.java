package nl.novi.eindopdrachtbackend.service;
import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.extraMethods.transferBookMethod;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.CatalogusRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogusService {

    private BookService bookService;

    private BookRepository bookRepository;
    private CatalogusRepository catalogusRepository;



    public CatalogusService(BookService bookService, BookRepository bookRepository, CatalogusRepository catalogusRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.catalogusRepository = catalogusRepository;
    }

    public ArrayList<BookDto> findBooksByAuthor(String author){

      ArrayList<Book> allBooksByAuthor = bookRepository.findBooksByAuthorContainingIgnoreCase(author);
      ArrayList<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : allBooksByAuthor){
          bookDtoList.add(outputTransferBookToDto(book));
      }
    return bookDtoList;

    }




    /////////////////////////////////////////////////////
    public BookDto outputTransferBookToDto(Book book) {
    BookDto bookDto = new BookDto();
    // de nieuwe dto vullen met de waardes uit Boo
    bookDto.isbn = book.getIsbn();
    bookDto.title = book.getTitle();
    bookDto.author = book.getAuthor();
    bookDto.year = book.getYear();
    bookDto.bookstatus = book.isBookstatus();
    bookDto.genre = book.getGenre();
        return bookDto;
}




}
