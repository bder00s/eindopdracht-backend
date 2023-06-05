package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.eindopdrachtbackend.enummeration.Genre;

@Getter
@Setter
@Entity
@Table(name = "books")


public class Book {

    // HIER KOMEN DE RELATIES

    @ManyToOne
    Borrowing borrowing;

    @ManyToOne
    Reservation reservation;


    // VARIABELEN //
    @Id
    @GeneratedValue
    private Long isbn;
    private String author;
    private String title;
    private int year;
    private boolean available;
    @Enumerated(value = EnumType.STRING)
    Genre genre;


    public Book(Long isbn, String author, String title, int year, boolean available, Genre genre) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
        this.available = available;
        this.genre = genre;
    }


    public Book() {

    }
}
