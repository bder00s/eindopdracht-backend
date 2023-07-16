package nl.novi.eindopdrachtbackend.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.novi.eindopdrachtbackend.enummeration.Genre;
import nl.novi.eindopdrachtbackend.model.Book;

public class BookDto {

    @GeneratedValue
    public Long isbn;
    @NotBlank
    public String author;
    @NotBlank
    public String title;

    public int year;

    @NotNull
    public boolean available;

    @Enumerated(value = EnumType.STRING)
    public Genre genre;


    public BookDto bookDto(Book book) {
        BookDto dto = new BookDto();
        dto.isbn = book.getIsbn();
        dto.author = book.getAuthor();
        dto.title = book.getTitle();
        dto.year = book.getYear();
        dto.available = book.isAvailable();
        dto.genre = book.getGenre();
        return dto;
    }


}
