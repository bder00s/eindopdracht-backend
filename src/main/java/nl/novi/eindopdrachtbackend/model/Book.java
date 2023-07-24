package nl.novi.eindopdrachtbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.novi.eindopdrachtbackend.enummeration.Genre;

@Getter
@Setter
@Entity
@Table(name = "books")


public class Book {


    // VARIABELEN //
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long isbn;
    @Column
    private String author;
    @Column
    private String title;
    @Column
    private int year;

    @Column
    private boolean available;
    @Enumerated(value = EnumType.STRING)
    Genre genre;

    @ManyToOne
    Loan loan;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonIgnore
    Reservation reservation;


    public Book(Long isbn, String author, String title, int year, boolean available, Genre genre, Reservation reservation) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.year = year;
        this.available = available;
        this.genre = genre;
        this.reservation = reservation;
    }


    public Book() {

    }
}
