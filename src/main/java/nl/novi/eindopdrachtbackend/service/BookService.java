package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.dto.BookOutputDto;
import nl.novi.eindopdrachtbackend.enummeration.Genre;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.model.Reservation;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private ReservationRepository reservationRepository;

    // INJECTION //
    public BookService(BookRepository bookRepository, ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
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
        book.setReservation(bookDto.reservation);

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
    public void updateBook(Long isbn, BookDto bookDto) {
        Book book = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book/id with: " + isbn + " not found!"));

        book.setIsbn(bookDto.isbn);
        book.setAuthor(bookDto.author);
        book.setTitle(bookDto.title);
        book.setYear(bookDto.year);
        book.setGenre(bookDto.genre);
        book.setAvailable(bookDto.available);
        book.setReservation(bookDto.reservation);
        bookRepository.save(book);
    }


    // GET ALL BOOKS - GET METHODE //
   public List<BookDto> getAllBooks(){
        List<BookDto> bookDtoList = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        for (Book book : bookList) {
            bookDtoList.add(outputTransferBookToDto(book));
        }
        return bookDtoList;
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
        BookOutputDto bookOutputDto = new BookOutputDto();

        bookDto.isbn = book.getIsbn();
        bookDto.title = book.getTitle();
        bookDto.author = book.getAuthor();
        bookDto.year = book.getYear();
        bookDto.available = book.isAvailable();
        bookDto.genre = book.getGenre();
        bookDto.reservation = book.getReservation();
        bookOutputDto.setReservationOutput(book.getReservation());
//        bookDto.reservation = bookOutputDto.setReservationOutput(book.getReservation()) ;
        return bookDto;
    }

    public void assignBookToReservation(Long reservationId, Long isbn) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        var optionalBook = bookRepository.findById(isbn);

        if(!optionalReservation.isEmpty()&& optionalBook.isPresent()) {
            var reservation = optionalReservation.get();
            var book = optionalBook.get();

            book.setReservation(reservation);
            book.setAvailable(false);
            // if statement if book available = false -> error message
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book/Reservation not found!");
        }
    }


//    public BookDto simpleOutput(Book book){
//        BookDto
//    }

}
