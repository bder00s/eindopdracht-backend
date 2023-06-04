package nl.novi.eindopdrachtbackend.model;

import nl.novi.eindopdrachtbackend.model.BookStatus;
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

    @OneToOne(cascade = CascadeType.ALL)
    BookStatus bookStatus;

    @ManyToOne
    Borrowing borrowing;

    @ManyToOne
    Reservation reservation;

    @ManyToOne
    Catalogus catalogus;

    // VARIABELEN //
    @Id
    @GeneratedValue
    private Long isbn;
    private String author;
    private String title;

    private int year;
    @Enumerated(value = EnumType.STRING)
    Genre genre;


    public Book(Long isbn, String author, String title, int year, Genre genre, BookStatus bookStatus) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.bookStatus = bookStatus;
    }

    public Book() {

    }


}
