package nl.novi.eindopdrachtbackend.dto;

import nl.novi.eindopdrachtbackend.model.Book;

import java.util.ArrayList;
import java.util.List;

public class CatalogusDto {

    public Long catalogusId;
    public ArrayList<Book> bookList;


    public CatalogusDto(Long catalogusId, ArrayList<Book> bookList) {
        this.catalogusId = catalogusId;
        this.bookList = bookList;
    }
}
