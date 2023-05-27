package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@Entity
@Table(name = "books")


public class Book {

    @Id
    @GeneratedValue
    private Long isbn;
    private String author;
    private String title;

    private int year;

    // HIER KOMEN DE RELATIES

    public Book(Long isbn, String author,  String title, int year) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public Book() {

    }


}
