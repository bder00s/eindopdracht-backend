package nl.novi.eindopdrachtbackend.extraMethods;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.model.Book;

public class transferBookMethod {

    // METHODE OM BOOK -> DTO OM TE ZETTEN - IS STEEDS OPNIEUW TE GEBRUIKEN
    // OUTPUT -> VAN SERVICE/DATABASE NAAR LID
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
