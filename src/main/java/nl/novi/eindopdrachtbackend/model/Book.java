package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    private String isbn;
    private String title;
    private String author;

}
