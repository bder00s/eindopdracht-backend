package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book_status")
public class BookStatus {

    @OneToOne(mappedBy = "bookStatus")
    Book book;

    @Id
    @GeneratedValue
    Long BookStatusId;
}
