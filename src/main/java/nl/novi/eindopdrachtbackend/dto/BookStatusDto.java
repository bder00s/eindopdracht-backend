package nl.novi.eindopdrachtbackend.dto;

import jakarta.validation.constraints.NotBlank;
import nl.novi.eindopdrachtbackend.model.Book;

public class BookStatusDto {

    public Long BookStatusId;

    @NotBlank
    public Boolean available;

    public Book book;

    public BookStatusDto(Long bookStatusId, Boolean available, Book book) {
        BookStatusId = bookStatusId;
        this.available = available;
        this.book = book;
    }
}
