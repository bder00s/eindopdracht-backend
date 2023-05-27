package nl.novi.eindopdrachtbackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.novi.eindopdrachtbackend.enummeration.Genre;
import nl.novi.eindopdrachtbackend.model.Book;

public class BookDto {

    public Long isbn;
    @NotBlank
    public String author;
    @NotBlank
    public String title;

    public int year;

    public Genre genre;


    public BookDto bookDto(Book book) {
        BookDto dto = new BookDto();
        dto.isbn = book.getIsbn();
        dto.author = book.getAuthor();
        dto.title = book.getTitle();
        dto.year = book.getYear();
        dto.genre = book.getGenre();
        return dto;
    }


}
