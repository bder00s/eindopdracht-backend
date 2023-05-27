package nl.novi.eindopdrachtbackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import nl.novi.eindopdrachtbackend.model.Book;

public class BookDto {

    public Long isbn;
    @NotBlank
    public String author;
    @NotBlank
    public String title;

    public int year;


    public BookDto bookDto(Book book) {
        BookDto dto = new BookDto();
        dto.isbn = book.getIsbn();
        dto.author = book.getAuthor();
        dto.title = book.getTitle();
        dto.year = book.getYear();
        return dto;
    }


}
