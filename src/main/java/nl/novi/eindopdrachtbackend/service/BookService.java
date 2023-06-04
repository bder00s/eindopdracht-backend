package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    // INJECTION //
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //ADD BOOK - POST METHODE //
    public String newBook(BookDto bookDto) {
        Book book = new Book();
        // VAN KLANT -> DATABASE -> INPUT //
//        book.setIsbn(bookDto.isbn);
        book.setAuthor(bookDto.author);
        book.setTitle(bookDto.title);
        book.setYear(bookDto.year);
        book.setGenre(bookDto.genre);
        book.setBookStatus(bookDto.bookStatus);

        bookRepository.save(book);
        String outcome = "Saved to library database:" +
                "\n isbn: " + book.getIsbn() +
                "\n title: " + book.getTitle() +
                "\n author: " + book.getAuthor() +
                "\n year: " + book.getYear() +
                "\n genre: " + book.getGenre() +
                "\n book available ? " + book.getBookStatus();
        return outcome;
    }

    public String updateBook(Long isbn, BookDto bookDto) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book/id with: " + isbn + " not found!"));

//        book.setIsbn(bookDto.isbn);
        book.setAuthor(bookDto.author);
        book.setTitle(bookDto.title);
        book.setYear(bookDto.year);
        book.setGenre(bookDto.genre);
        book.setBookStatus(bookDto.bookStatus);
        bookRepository.save(book);

        return "Book updated!: " +
                "\n isbn: " + book.getIsbn() +
                "\n title: " + book.getTitle() +
                "\n author: " + book.getAuthor() +
                "\n year: " + book.getYear() +
                "\n genre: " + book.getGenre() +
                "\n book available ? " + book.getBookStatus();
    }

    // DELETE BOOK - DELETE METHODE //
    public String deleteBook(Long isbn){
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book/id with: " + isbn + " not found!"));
        bookRepository.delete(book);
        return "Book with " + isbn + " successfully deleted!";
    }





}
