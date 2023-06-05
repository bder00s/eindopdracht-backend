package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.enummeration.Genre;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        book.setAvailable(bookDto.available);

        bookRepository.save(book);
        String outcome = "Saved to library database:" +
                "\n isbn: " + book.getIsbn() +
                "\n title: " + book.getTitle() +
                "\n author: " + book.getAuthor() +
                "\n year: " + book.getYear() +
                "\n genre: " + book.getGenre() +
                "\n book available ? " + book.isAvailable();
        return outcome;
    }

    //UPDATE BOOK - PUT METHODE //
    public String updateBook(Long isbn, BookDto bookDto) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book/id with: " + isbn + " not found!"));

//        book.setIsbn(bookDto.isbn);
        book.setAuthor(bookDto.author);
        book.setTitle(bookDto.title);
        book.setYear(bookDto.year);
        book.setGenre(bookDto.genre);
        book.setAvailable(bookDto.available);
        bookRepository.save(book);

        // ISBN RETURNS NULL WHEN USING PUT METHOD

        return "Book updated!: " +
                "\n isbn: " + book.getIsbn() +
                "\n title: " + book.getTitle() +
                "\n author: " + book.getAuthor() +
                "\n year: " + book.getYear() +
                "\n genre: " + book.getGenre() +
                "\n book available ? " + book.isAvailable();
    }

    //UPDATE BOOKSTATUS - PUT METHODE //
    public String newBookStatus(Long isbn, BookDto bookDto) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book/id with: " + isbn + " not found!"));
        book.setAvailable(bookDto.available);
        bookRepository.save(book);
        return "Status from book " + isbn + " updated to: " + book.isAvailable();


        //EVERYTHING ELSE RETURNS NULL WHEN USING PUT METHOD TO CHANGE STATUS
    }


    //GET BOOKS BY AUTHOR - GET METHODE //
    public ArrayList<BookDto> findBooksByAuthor(String author){

        ArrayList<Book> allBooksByAuthor = bookRepository.findBooksByAuthorContainingIgnoreCase(author);
        ArrayList<BookDto> bookAuthors = new ArrayList<>();
        for (Book book : allBooksByAuthor){
            bookAuthors.add(outputTransferBookToDto(book));
        }
        return bookAuthors;
    }

    //GET BOOKS BY TITLE - GET METHODE //
    public ArrayList<BookDto> findBooksByTitle(String title){
        ArrayList<Book> allBooksByTitle = bookRepository.findBooksByTitleContainingIgnoreCase(title);
        ArrayList<BookDto> bookTitles = new ArrayList<>();
        for (Book book : allBooksByTitle){
            bookTitles.add(outputTransferBookToDto(book));
        }
        return bookTitles;
    }

    //GET BOOKS BY GENRE - GET METHODE //
    public ArrayList<BookDto> findBooksByGenre(Genre genre){
        ArrayList<Book> allBooksByGenre = bookRepository.findByGenre(genre);
        ArrayList<BookDto>allGenres = new ArrayList<>();
        for (Book book : allBooksByGenre){
            allGenres.add(outputTransferBookToDto(book));
        }
        return allGenres;
    }


    // DELETE BOOK - DELETE METHODE //
    public String deleteBook(Long isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book/id with: " + isbn + " not found!"));
        bookRepository.delete(book);
        return "Book with " + isbn + " successfully deleted!";
    }


    public BookDto outputTransferBookToDto(Book book) {
        BookDto bookDto = new BookDto();
        // de nieuwe dto vullen met de waardes uit Boo
        bookDto.isbn = book.getIsbn();
        bookDto.title = book.getTitle();
        bookDto.author = book.getAuthor();
        bookDto.year = book.getYear();
        bookDto.available = book.isAvailable();
        bookDto.genre = book.getGenre();
        return bookDto;
    }

}
